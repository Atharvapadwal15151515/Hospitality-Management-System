package test;

import dao.ReservationDAO;
import model.Reservation;

import java.sql.Date;
import java.util.List;

@SuppressWarnings("unused")
public class TestReservationOperations {
    public static void main(String[] args) {
        ReservationDAO dao = new ReservationDAO();

     
    //    Date checkIn = Date.valueOf("2025-07-15");
    //   Date checkOut = Date.valueOf("2025-07-20");
    //   Reservation r = new Reservation(2, 2, checkIn, checkOut);
    // dao.addReservation(r);

        
  //     List<Reservation> list = dao.getAllReservations();
  //    for (Reservation res : list) {
  //       System.out.println("ID: " + res.getId());
  //         System.out.println("Guest ID: " + res.getGuestId());
  //       System.out.println("Room ID: " + res.getRoomId());
  //         System.out.println("Check-In: " + res.getCheckIn());
  //        System.out.println("Check-Out: " + res.getCheckOut());
  //          System.out.println("--------------------------");
   //     }

   //   Date newCheckIn = Date.valueOf("2025-07-18");
   //     Date newCheckOut = Date.valueOf("2025-07-22");
   //     Reservation updated = new Reservation(1, 1, 1, newCheckIn, newCheckOut); // id, guest_id, room_id
   //     dao.updateReservation(updated);

       //      dao.deleteReservation(1);
    }
}
