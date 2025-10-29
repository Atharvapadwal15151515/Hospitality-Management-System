package test;

import dao.RoomDAO;
import model.Room;

public class TestAddRoom {
    public static void main(String[] args) {
        Room r = new Room(1, "101", "Deluxe", 4500.00, "Available");
        RoomDAO dao = new RoomDAO();
        dao.addRoom(r);
    }
}
