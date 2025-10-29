package model;

import java.sql.Date;

public class Reservation {
    private int id;
    private int guestId;
    private int roomId;
    private Date checkIn;
    private Date checkOut;

    // Constructor with ID (used when fetching from DB)
    public Reservation(int id, int guestId, int roomId, Date checkIn, Date checkOut) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Constructor without ID (used when creating a new reservation)
    public Reservation(int guestId, int roomId, Date checkIn, Date checkOut) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGuestId() { return guestId; }
    public void setGuestId(int guestId) { this.guestId = guestId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public Date getCheckIn() { return checkIn; }
    public void setCheckIn(Date checkIn) { this.checkIn = checkIn; }

    public Date getCheckOut() { return checkOut; }
    public void setCheckOut(Date checkOut) { this.checkOut = checkOut; }
}
