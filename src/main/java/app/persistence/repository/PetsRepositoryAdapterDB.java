package app.persistence.repository;

import app.domain.Pets;
import app.domain.Tutor;
import app.persistence.dbconnection.DataBaseConnection;
import app.persistence.mappers.PetsMapper;
import app.service.outputports.PetsRepository;
import app.service.outputports.TutorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetsRepositoryAdapterDB implements PetsRepository {

    private final Connection connection;
    private final TutorRepository tutorRepository;

    public PetsRepositoryAdapterDB(DataBaseConnection dbConnection, TutorRepository tutorRepository) {
        this.connection = dbConnection.getConnection();
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Pets save(Pets pet) {
        String sql = "INSERT INTO pets (name, species, breed, sex, weight, birth_date, tutor_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setPetParams(ps, pet);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    pet.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la mascota: " + e.getMessage());
        }
        return pet;
    }

    @Override
    public List<Pets> findAll() {
        String sql = "SELECT * FROM pets";
        List<Pets> pets = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Tutor tutor = tutorRepository.findById(rs.getInt("tutor_id"));
                pets.add(PetsMapper.toDomain(rs, tutor));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las mascotas: " + e.getMessage());
        }
        return pets;
    }

    @Override
    public Pets findById(int id) {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tutor tutor = tutorRepository.findById(rs.getInt("tutor_id"));
                    return PetsMapper.toDomain(rs, tutor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la mascota: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Pets update(Pets pet) {
        String sql = "UPDATE pets SET name = ?, species = ?, breed = ?, sex = ?, "
                + "weight = ?, birth_date = ?, tutor_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            setPetParams(ps, pet);
            ps.setInt(8, pet.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la mascota: " + e.getMessage());
        }
        return pet;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la mascota: " + e.getMessage());
        }
    }

    private void setPetParams(PreparedStatement ps, Pets pet) throws SQLException {
        ps.setString(1, pet.getName());
        ps.setString(2, pet.getSpecies());
        ps.setString(3, pet.getBreed());
        ps.setBoolean(4, pet.isSex());
        ps.setDouble(5, pet.getWeight());
        if (pet.getBirthDate() != null) {
            ps.setDate(6, Date.valueOf(pet.getBirthDate()));
        } else {
            ps.setNull(6, Types.DATE);
        }
        ps.setInt(7, pet.getTutor().getId());
    }
}