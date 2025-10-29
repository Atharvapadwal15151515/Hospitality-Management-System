package test;

import dao.HotelDAO;

public class TestDeleteHotel {
    public static void main(String[] args) {
        HotelDAO dao = new HotelDAO();
        dao.deleteHotel(2); // change to the ID you want to delete
    }
}
