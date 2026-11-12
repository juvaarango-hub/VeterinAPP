package app.persistence.repository;

import app.domain.Tutor;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mappers.TutorMapper;
import app.service.outputports.TutorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TutorRepositoryAdapterDB implements TutorRepository { // tu interfaz de outputports

    private final Connection connection;

    public TutorRepositoryAdapterDB(DataBaseConnection dbConnection) {
        this.connection = dbConnection.getConnection();
    }

    @Override
    public Tutor save(Tutor tutor) {
        String sql = "INSERT INTO tutor (id, name, last_name, email, phone, password, state, direction) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, tutor.getId());
            ps.setString(2, tutor.getName());
            ps.setString(3, tutor.getLastName());
            ps.setString(4, tutor.getEmail());
            ps.setString(5, tutor.getPhone());
            ps.setString(6, tutor.getPassword());
            ps.setBoolean(7, tutor.isState());
            ps.setString(8, tutor.getDirection());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el tutor: " + e.getMessage());
        }
        return tutor;
    }

    @Override
    public List<Tutor> findAll() {
        String sql = "SELECT * FROM tutor";
        List<Tutor> tutors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tutors.add(TutorMapper.toDomain(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar los tutores: " + e.getMessage());
        }
        return tutors;
    }

    @Override
    public Tutor findById(int id) {
        String sql = "SELECT * FROM tutor WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return TutorMapper.toDomain(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el tutor: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Tutor update(Tutor tutor) {
        String sql = "UPDATE tutor SET name = ?, last_name = ?, email = ?, phone = ?, "
                + "password = ?, state = ?, direction = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tutor.getName());
            ps.setString(2, tutor.getLastName());
            ps.setString(3, tutor.getEmail());
            ps.setString(4, tutor.getPhone());
            ps.setString(5, tutor.getPassword());
            ps.setBoolean(6, tutor.isState());
            ps.setString(7, tutor.getDirection());
            ps.setInt(8, tutor.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el tutor: " + e.getMessage());
        }
        return tutor;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM tutor WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el tutor: " + e.getMessage());
        }
    }

}