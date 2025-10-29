package test;

import dao.HotelDAO;
import model.Hotel;

public class TestUpdateHotel {
    public static void main(String[] args) {
        // ID of hotel you want to update + new values
        Hotel updatedHotel = new Hotel(1, "The Royal Palace", "Delhi", "Spa, Pool, Free Wi-Fi");
        HotelDAO dao = new HotelDAO();
        dao.updateHotel(updatedHotel);
    }
}
