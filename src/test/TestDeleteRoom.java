package test;

import dao.RoomDAO;

public class TestDeleteRoom {
    public static void main(String[] args) {
        RoomDAO dao = new RoomDAO();
        dao.deleteRoom(1); // use the ID you want to delete
    }
}
