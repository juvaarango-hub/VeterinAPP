package app.persistence.mappers;

import app.domain.Appointment;
import app.domain.ClinicHistory;
import app.domain.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClinicHistoryMapper {

    public static ClinicHistory toDomain(ResultSet rs, Appointment appointment, Doctor doctor) throws SQLException {
        return new ClinicHistory(
                appointment,
                rs.getTimestamp("date_time").toLocalDateTime(),
                rs.getString("subjective"),
                rs.getString("objective"),
                rs.getString("interpretation"),
                rs.getString("plan"),
                rs.getString("observation"),
                doctor
        );
    }
}