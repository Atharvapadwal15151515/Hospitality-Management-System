package dao;

import model.Room;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void addRoom(Room room) {
        String sql = "INSERT INTO rooms (hotel_id, room_number, type, price, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getType());
            stmt.setDouble(4, room.getPrice());
            stmt.setString(5, room.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Room added successfully.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error adding room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM rooms";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int hotelId = rs.getInt("hotel_id");
                String roomNumber = rs.getString("room_number");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                String status = rs.getString("status");

                Room r = new Room(id, hotelId, roomNumber, type, price, status);
                roomList.add(r);
            }

        } catch (Exception e) {
            System.out.println("❌ Error retrieving rooms: " + e.getMessage());
            e.printStackTrace();
        }

        return roomList;
    }

    public void deleteRoom(int id) {
        String sql = "DELETE FROM rooms WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("✅ Room deleted successfully.");
            } else {
                System.out.println("⚠️ No room found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error deleting room: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateRoom(Room room) {
        String sql = "UPDATE rooms SET hotel_id = ?, room_number = ?, type = ?, price = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, room.getHotelId());
            stmt.setString(2, room.getRoomNumber());
            stmt.setString(3, room.getType());
            stmt.setDouble(4, room.getPrice());
            stmt.setString(5, room.getStatus());
            stmt.setInt(6, room.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Room updated successfully.");
            } else {
                System.out.println("⚠️ No room found with that ID.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error updating room: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
