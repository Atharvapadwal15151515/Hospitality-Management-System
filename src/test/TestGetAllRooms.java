package test;

import dao.RoomDAO;
import model.Room;

import java.util.List;

public class TestGetAllRooms {
    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();
        List<Room> rooms = dao.getAllRooms();

        for (Room r : rooms) {
            System.out.println("ID: " + r.getId());
            System.out.println("Hotel ID: " + r.getHotelId());
            System.out.println("Room No: " + r.getRoomNumber());
            System.out.println("Type: " + r.getType());
            System.out.println("Price: " + r.getPrice());
            System.out.println("Status: " + r.getStatus());
            System.out.println("----------------------");
        }
    }
}
