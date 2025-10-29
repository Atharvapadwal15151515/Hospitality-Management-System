package test;

import dao.HotelDAO;
import model.Hotel;

import java.util.List;

public class TestGetAllHotels {
    public static void main(String[] args) {
        HotelDAO dao = new HotelDAO();
        List<Hotel> hotels = dao.getAllHotels();

        for (Hotel h : hotels) {
            System.out.println("ID: " + h.getId());
            System.out.println("Name: " + h.getName());
            System.out.println("Location: " + h.getLocation());
            System.out.println("Amenities: " + h.getAmenities());
            System.out.println("-------------------------");
        }
    }
}
