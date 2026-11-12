package app.persistence.repository;

import app.domain.Doctor;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mappers.DoctorMapper;
import app.service.outputports.DoctorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryAdapterDB implements DoctorRepository {

    private final Connection connection;

    public DoctorRepositoryAdapterDB(DataBaseConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public Doctor save(Doctor doctor) {
        String sql = "INSERT INTO doctor (name, last_name, email, phone, password, state, id_mp, specialty, id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setDoctorParams(ps, doctor);
            ps.setInt(9, doctor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el doctor: " + e.getMessage());
        }
        return doctor;
    }

    @Override
    public List<Doctor> findAll() {
        String sql = "SELECT * FROM doctor";
        List<Doctor> doctors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                doctors.add(DoctorMapper.toDomain(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los doctores: " + e.getMessage());
        }
        return doctors;
    }

    @Override
    public Doctor findById(int id) {
        String sql = "SELECT * FROM doctor WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return DoctorMapper.toDomain(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el doctor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Doctor update(Doctor doctor) {
        String sql = "UPDATE doctor SET name = ?, last_name = ?, email = ?, phone = ?, "
                + "password = ?, state = ?, id_mp = ?, specialty = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setDoctorParams(ps, doctor);
            ps.setInt(9, doctor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el doctor: " + e.getMessage());
        }
        return doctor;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM doctor WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el doctor: " + e.getMessage());
        }
    }

    private void setDoctorParams(PreparedStatement ps, Doctor doctor) throws SQLException {
        ps.setString(1, doctor.getName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getEmail());
        ps.setString(4, doctor.getPhone());
        ps.setString(5, doctor.getPassword());
        ps.setBoolean(6, doctor.isState());
        ps.setInt(7, doctor.getIdMp());
        ps.setString(8, doctor.getSpecialty());
    }
}