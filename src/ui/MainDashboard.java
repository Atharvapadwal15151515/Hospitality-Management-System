package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({ "unused", "serial" })
public class MainDashboard extends JFrame {
    public MainDashboard() {
        setTitle("Hospitality Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Hospitality Management System", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 16));
        heading.setBounds(20, 10, 360, 30);
        add(heading);

        JButton hotelBtn = new JButton("Hotel Management");
        hotelBtn.setBounds(100, 60, 200, 30);
        add(hotelBtn);

        JButton roomBtn = new JButton("Room Management");
        roomBtn.setBounds(100, 100, 200, 30);
        add(roomBtn);

        JButton guestBtn = new JButton("Guest Management");
        guestBtn.setBounds(100, 140, 200, 30);
        add(guestBtn);

        JButton resBtn = new JButton("Reservation Management");
        resBtn.setBounds(100, 180, 200, 30);
        add(resBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(100, 220, 200, 30);
        add(exitBtn);

        // Button actions to open forms
        hotelBtn.addActionListener(e -> new HotelForm());
        roomBtn.addActionListener(e -> new RoomForm());
        guestBtn.addActionListener(e -> new GuestForm());
        resBtn.addActionListener(e -> new ReservationForm());
        exitBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
