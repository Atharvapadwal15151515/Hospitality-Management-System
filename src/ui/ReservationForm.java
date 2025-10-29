package ui;

import dao.ReservationDAO;
import model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.List;

public class ReservationForm extends JFrame {
    private JTextField guestIdField, roomIdField, checkInField, checkOutField;
    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public ReservationForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Reservation Management");
        setSize(750, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // 🔹 Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        guestIdField = new JTextField(15);
        roomIdField = new JTextField(15);
        checkInField = new JTextField(15);
        checkOutField = new JTextField(15);

        JLabel guestLabel = new JLabel("Guest ID:");
        JLabel roomLabel = new JLabel("Room ID:");
        JLabel inLabel = new JLabel("Check-In (YYYY-MM-DD):");
        JLabel outLabel = new JLabel("Check-Out (YYYY-MM-DD):");

        JLabel[] labels = { guestLabel, roomLabel, inLabel, outLabel };
        JTextField[] fields = { guestIdField, roomIdField, checkInField, checkOutField };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(font);
            fields[i].setFont(font);

            gbc.gridx = 0; gbc.gridy = i;
            inputPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            inputPanel.add(fields[i], gbc);
        }

        // 🔹 Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = new JButton("Add");
        JButton viewButton = new JButton("View");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        JButton[] buttons = { addButton, viewButton, updateButton, deleteButton };
        for (JButton btn : buttons) {
            btn.setFont(font);
            buttonPanel.add(btn);
        }

        gbc.gridx = 0; gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Table
        String[] columns = { "ID", "Guest ID", "Room ID", "Check-In", "Check-Out" };
        tableModel = new DefaultTableModel(columns, 0);
        reservationTable = new JTable(tableModel);
        reservationTable.setFont(font);
        reservationTable.setRowHeight(22);
        add(new JScrollPane(reservationTable), BorderLayout.CENTER);

        // 🔹 Add Reservation
        addButton.addActionListener(e -> {
            try {
                int guestId = Integer.parseInt(guestIdField.getText());
                int roomId = Integer.parseInt(roomIdField.getText());
                Date checkIn = Date.valueOf(checkInField.getText());
                Date checkOut = Date.valueOf(checkOutField.getText());

                Reservation r = new Reservation(guestId, roomId, checkIn, checkOut);
                new ReservationDAO().addReservation(r);
                JOptionPane.showMessageDialog(this, "✅ Reservation added!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        // 🔹 View Reservations
        viewButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            List<Reservation> list = new ReservationDAO().getAllReservations();
            for (Reservation r : list) {
                Object[] row = {
                    r.getId(),
                    r.getGuestId(),
                    r.getRoomId(),
                    r.getCheckIn(),
                    r.getCheckOut()
                };
                tableModel.addRow(row);
            }
        });

        // 🔹 Update Reservation
        updateButton.addActionListener(e -> {
            int row = reservationTable.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) tableModel.getValueAt(row, 0);
                    int guestId = Integer.parseInt(guestIdField.getText());
                    int roomId = Integer.parseInt(roomIdField.getText());
                    Date checkIn = Date.valueOf(checkInField.getText());
                    Date checkOut = Date.valueOf(checkOutField.getText());

                    Reservation updated = new Reservation(id, guestId, roomId, checkIn, checkOut);
                    new ReservationDAO().updateReservation(updated);
                    JOptionPane.showMessageDialog(this, "✅ Reservation updated!");
                    viewButton.doClick();
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "❌ Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to update.");
            }
        });

        // 🔹 Delete Reservation
        deleteButton.addActionListener(e -> {
            int row = reservationTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Delete reservation ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new ReservationDAO().deleteReservation(id);
                    tableModel.removeRow(row);
                    JOptionPane.showMessageDialog(this, "✅ Reservation deleted.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to delete.");
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        guestIdField.setText("");
        roomIdField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
    }

    public static void main(String[] args) {
        new ReservationForm();
    }
}

