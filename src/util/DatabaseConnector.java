package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/hospitality_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Atharva123#"; 

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
