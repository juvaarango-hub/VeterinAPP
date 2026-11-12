package app.persistence.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private final Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/veterinapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    public DataBaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Se estableció conexión con la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static synchronized DataBaseConnection getInstance(){
        if(instance == null){
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

