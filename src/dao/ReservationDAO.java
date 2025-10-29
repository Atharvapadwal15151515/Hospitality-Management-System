package dao;

import model.Reservation;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public void addReservation(Reservation r) {
        String sql = "INSERT INTO reservations (guest_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getGuestId());
            stmt.setInt(2, r.getRoomId());
            stmt.setDate(3, r.getCheckIn());
            stmt.setDate(4, r.getCheckOut());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Reservation added successfully.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error adding reservation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation r = new Reservation(
                    rs.getInt("id"),
                    rs.getInt("guest_id"),
                    rs.getInt("room_id"),
                    rs.getDate("check_in"),
                    rs.getDate("check_out")
                );
                reservations.add(r);
            }

        } catch (Exception e) {
            System.out.println("❌ Error retrieving reservations: " + e.getMessage());
            e.printStackTrace();
        }

        return reservations;
    }

    public void updateReservation(Reservation r) {
        String sql = "UPDATE reservations SET guest_id = ?, room_id = ?, check_in = ?, check_out = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getGuestId());
            stmt.setInt(2, r.getRoomId());
            stmt.setDate(3, r.getCheckIn());
            stmt.setDate(4, r.getCheckOut());
            stmt.setInt(5, r.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Reservation updated successfully.");
            } else {
                System.out.println("⚠️ No reservation found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error updating reservation: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Reservation deleted successfully.");
            } else {
                System.out.println("⚠️ No reservation found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error deleting reservation: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
