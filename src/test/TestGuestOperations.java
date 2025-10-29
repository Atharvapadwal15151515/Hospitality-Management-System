package test;

import dao.GuestDAO;
import model.Guest;

import java.util.List;

@SuppressWarnings("unused")
public class TestGuestOperations {
    public static void main(String[] args) {
        GuestDAO dao = new GuestDAO();

        
     Guest newGuest = new Guest("Atharva", "atharva@example.com", "9876543210");
     dao.addGuest(newGuest);

    
//        List<Guest> guests = dao.getAllGuests();
//        for (Guest g : guests) {
 //           System.out.println("ID: " + g.getId());
 //          System.out.println("Name: " + g.getName());
   //         System.out.println("Email: " + g.getEmail());
     //       System.out.println("Phone: " + g.getPhone());
       //     System.out.println("-----------------------");
     // }

     
//      Guest updatedGuest = new Guest(1, "Atharva Patil", "patil@example.com", "9999988888");
//       dao.updateGuest(updatedGuest);

       
//     dao.deleteGuest(1); // use the ID of a real guest
    }
}
