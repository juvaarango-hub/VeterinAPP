package app.persistence.mappers;

import app.domain.Doctor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorMapper {

    public static Doctor toDomain(ResultSet rs) throws SQLException {
        return new Doctor(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getBoolean("state"),
                rs.getInt("id_mp"),
                rs.getString("specialty")
        );
    }
}
