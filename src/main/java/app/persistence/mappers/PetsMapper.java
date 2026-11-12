package app.persistence.mappers;

import app.domain.Pets;
import app.domain.Tutor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PetsMapper {

    public static Pets toDomain(ResultSet rs, Tutor tutor) throws SQLException {
        Date date = rs.getDate("birth_date");
        LocalDate birthDate = (date != null) ? date.toLocalDate() : null;

        return new Pets(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("species"),
                rs.getString("breed"),
                rs.getBoolean("sex"),
                rs.getDouble("weight"),
                birthDate,
                tutor
        );
    }
}