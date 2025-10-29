package test;

import dao.RoomDAO;
import model.Room;

public class TestUpdateRoom {
    public static void main(String[] args) {
        Room updatedRoom = new Room(1, 1, "101-A", "Super Deluxe", 5000.00, "Booked");
        RoomDAO dao = new RoomDAO();
        dao.updateRoom(updatedRoom);
    }
}
