package app.persistence.mappers;

import app.domain.Tutor;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorMapper {

    public static Tutor toDomain(ResultSet rs) throws SQLException {
        return new Tutor(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("password"),
                rs.getBoolean("state"),
                rs.getString("direction")
        );
    }
}
