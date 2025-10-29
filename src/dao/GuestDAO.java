package dao;

import model.Guest;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    public void addGuest(Guest guest) {
        String sql = "INSERT INTO guests (name, email, phone) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Guest added successfully.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error adding guest: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guests";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Guest g = new Guest(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
                );
                guests.add(g);
            }

        } catch (Exception e) {
            System.out.println("❌ Error retrieving guests: " + e.getMessage());
            e.printStackTrace();
        }

        return guests;
    }

    public void updateGuest(Guest guest) {
        String sql = "UPDATE guests SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, guest.getName());
            stmt.setString(2, guest.getEmail());
            stmt.setString(3, guest.getPhone());
            stmt.setInt(4, guest.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Guest updated successfully.");
            } else {
                System.out.println("⚠️ No guest found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error updating guest: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteGuest(int id) {
        String sql = "DELETE FROM guests WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Guest deleted successfully.");
            } else {
                System.out.println("⚠️ No guest found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error deleting guest: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
