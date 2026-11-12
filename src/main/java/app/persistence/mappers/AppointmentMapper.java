package app.persistence.mappers;

import app.domain.Appointment;
import app.domain.Pets;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper {

    public static Appointment toDomain(ResultSet rs, Pets pet) throws SQLException {
        return new Appointment(
                rs.getInt("id"),
                rs.getDate("appointment_date"),
                rs.getTime("appointment_time"),
                rs.getBoolean("state"),
                rs.getString("direction"),
                rs.getString("observations"),
                pet
        );
    }
}