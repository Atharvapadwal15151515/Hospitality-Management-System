package dao;

import model.Hotel;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    public void addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotels (name, location, amenities) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotel.getName());
            stmt.setString(2, hotel.getLocation());
            stmt.setString(3, hotel.getAmenities());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Hotel added successfully.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error adding hotel: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM hotels";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                String amenities = rs.getString("amenities");

                Hotel h = new Hotel(id, name, location, amenities);
                hotelList.add(h);
            }

        } catch (Exception e) {
            System.out.println("❌ Error retrieving hotels: " + e.getMessage());
            e.printStackTrace();
        }

        return hotelList;
    }


public void deleteHotel(int id) {
    String sql = "DELETE FROM hotels WHERE id = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        int rowsDeleted = stmt.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("✅ Hotel deleted successfully.");
        } else {
            System.out.println("⚠️ No hotel found with that ID.");
        }

    } catch (Exception e) {
        System.out.println("❌ Error deleting hotel: " + e.getMessage());
        e.printStackTrace();
    }
}


public void updateHotel(Hotel hotel) {
    String sql = "UPDATE hotels SET name = ?, location = ?, amenities = ? WHERE id = ?";

    try (Connection conn = DatabaseConnector.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, hotel.getName());
        stmt.setString(2, hotel.getLocation());
        stmt.setString(3, hotel.getAmenities());
        stmt.setInt(4, hotel.getId());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("✅ Hotel updated successfully.");
        } else {
            System.out.println("⚠️ No hotel found with that ID.");
        }

    } catch (Exception e) {
        System.out.println("❌ Error updating hotel: " + e.getMessage());
        e.printStackTrace();
    }
}
}

