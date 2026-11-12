package app.persistence.repository;

import app.domain.Appointment;
import app.domain.ClinicHistory;
import app.domain.Doctor;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mappers.ClinicHistoryMapper;
import app.service.outputports.AppointmentRepository;
import app.service.outputports.ClinicHistoryRepository;
import app.service.outputports.DoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ClinicHistoryRepositoryAdapterDB implements ClinicHistoryRepository {

    private final Connection connection;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public ClinicHistoryRepositoryAdapterDB(DataBaseConnection dbConnection,
                                            AppointmentRepository appointmentRepository,
                                            DoctorRepository doctorRepository) {
        this.connection = dbConnection.getConnection();
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public ClinicHistory save(ClinicHistory history) {
        String sql = "INSERT INTO clinic_history (date_time, subjective, objective, interpretation, "
                + "plan, observation, doctor_id, appointment_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setHistoryParams(ps, history);
            ps.setInt(8, history.getAppointment().getIdAppointment());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la historia clínica: " + e.getMessage());
        }
        return history;
    }

    @Override
    public List<ClinicHistory> findAll() {
        String sql = "SELECT * FROM clinic_history";
        List<ClinicHistory> histories = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Appointment appointment = appointmentRepository.findById(rs.getInt("appointment_id"));
                Doctor doctor = doctorRepository.findById(rs.getInt("doctor_id"));
                histories.add(ClinicHistoryMapper.toDomain(rs, appointment, doctor));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las historias clínicas: " + e.getMessage());
        }
        return histories;
    }

    @Override
    public ClinicHistory findById(int appointmentId) {
        String sql = "SELECT * FROM clinic_history WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Appointment appointment = appointmentRepository.findById(appointmentId);
                    Doctor doctor = doctorRepository.findById(rs.getInt("doctor_id"));
                    return ClinicHistoryMapper.toDomain(rs, appointment, doctor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la historia clínica: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ClinicHistory update(ClinicHistory history) {
        String sql = "UPDATE clinic_history SET date_time = ?, subjective = ?, objective = ?, "
                + "interpretation = ?, plan = ?, observation = ?, doctor_id = ? WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setHistoryParams(ps, history);
            ps.setInt(8, history.getAppointment().getIdAppointment());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la historia clínica: " + e.getMessage());
        }
        return history;
    }

    @Override
    public boolean deleteById(int appointmentId) {
        String sql = "DELETE FROM clinic_history WHERE appointment_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la historia clínica: " + e.getMessage());
        }
    }

    private void setHistoryParams(PreparedStatement ps, ClinicHistory history) throws SQLException {
        ps.setTimestamp(1, Timestamp.valueOf(history.getDate()));
        ps.setString(2, history.getSubjective());
        ps.setString(3, history.getObjective());
        ps.setString(4, history.getInterpretation());
        ps.setString(5, history.getPlan());
        ps.setString(6, history.getObservation());
        ps.setInt(7, history.getDoctor().getId());
    }
}