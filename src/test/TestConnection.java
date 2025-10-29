package test;
import java.sql.Connection;
import util.DatabaseConnector;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL driver loaded!");
            
            Connection conn = DatabaseConnector.getConnection();
            System.out.println("✅ Connected to MySQL successfully!");
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
        } catch (Exception e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
