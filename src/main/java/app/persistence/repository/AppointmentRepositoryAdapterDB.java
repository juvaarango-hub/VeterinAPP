package app.persistence.repository;

import app.domain.Appointment;
import app.domain.Pets;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mappers.AppointmentMapper;
import app.service.outputports.AppointmentRepository;
import app.service.outputports.PetsRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepositoryAdapterDB implements AppointmentRepository {

    private final Connection connection;
    private final PetsRepository petsRepository;

    public AppointmentRepositoryAdapterDB(DataBaseConnection dbConnection, PetsRepository petsRepository) {
        this.connection = dbConnection.getConnection();
        this.petsRepository = petsRepository;
    }

    @Override
    public Appointment save(Appointment appointment) {
        String sql = "INSERT INTO appointment (appointment_date, appointment_time, state, direction, "
                + "observations, pet_id, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setAppointmentParams(ps, appointment);
            ps.setInt(7, appointment.getIdAppointment());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la cita: " + e.getMessage());
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        String sql = "SELECT * FROM appointment";
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pets pet = petsRepository.findById(rs.getInt("pet_id"));
                appointments.add(AppointmentMapper.toDomain(rs, pet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las citas: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public Appointment findById(int id) {
        String sql = "SELECT * FROM appointment WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pets pet = petsRepository.findById(rs.getInt("pet_id"));
                    return AppointmentMapper.toDomain(rs, pet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la cita: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) {
        String sql = "UPDATE appointment SET appointment_date = ?, appointment_time = ?, state = ?, "
                + "direction = ?, observations = ?, pet_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setAppointmentParams(ps, appointment);
            ps.setInt(7, appointment.getIdAppointment());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la cita: " + e.getMessage());
        }
        return appointment;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM appointment WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la cita: " + e.getMessage());
        }
    }

    private void setAppointmentParams(PreparedStatement ps, Appointment appointment) throws SQLException {
        ps.setDate(1, new Date(appointment.getDate().getTime()));
        ps.setTime(2, appointment.getTime());
        ps.setBoolean(3, appointment.getState());
        ps.setString(4, appointment.getDirection());
        ps.setString(5, appointment.getObservations());
        ps.setInt(6, appointment.getPet().getId());
    }
}