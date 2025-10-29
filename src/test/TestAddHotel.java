package test;

import dao.HotelDAO;
import model.Hotel;

public class TestAddHotel {
    public static void main(String[] args) {
        Hotel h = new Hotel("The Grand Palace", "Mumbai", "Pool, Wi-Fi, Gym");
        HotelDAO dao = new HotelDAO();
        dao.addHotel(h);
    }
}
