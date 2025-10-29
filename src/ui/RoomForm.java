package ui;

import dao.RoomDAO;
import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RoomForm extends JFrame {
    private JTextField hotelIdField, roomNumberField, typeField, priceField, statusField;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    public RoomForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Room Management");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // center screen
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // 🔹 Input Panel (GridBag)
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        hotelIdField = new JTextField(15);
        roomNumberField = new JTextField(15);
        typeField = new JTextField(15);
        priceField = new JTextField(15);
        statusField = new JTextField(15);

        JLabel[] labels = {
            new JLabel("Hotel ID:"), new JLabel("Room Number:"),
            new JLabel("Type:"), new JLabel("Price:"), new JLabel("Status:")
        };
        JTextField[] fields = {
            hotelIdField, roomNumberField, typeField, priceField, statusField
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(font);
            fields[i].setFont(font);

            gbc.gridx = 0;
            gbc.gridy = i;
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

        gbc.gridx = 0;
        gbc.gridy = labels.length;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Table
        String[] columns = { "ID", "Hotel ID", "Room No", "Type", "Price", "Status" };
        tableModel = new DefaultTableModel(columns, 0);
        roomTable = new JTable(tableModel);
        roomTable.setFont(font);
        roomTable.setRowHeight(22);
        add(new JScrollPane(roomTable), BorderLayout.CENTER);

        // 🔹 Add Room
        addButton.addActionListener(e -> {
            try {
                int hotelId = Integer.parseInt(hotelIdField.getText());
                String roomNo = roomNumberField.getText();
                String type = typeField.getText();
                double price = Double.parseDouble(priceField.getText());
                String status = statusField.getText();

                Room room = new Room(hotelId, roomNo, type, price, status);
                new RoomDAO().addRoom(room);
                JOptionPane.showMessageDialog(this, "✅ Room added!");
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
            }
        });

        // 🔹 View Rooms
        viewButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            List<Room> rooms = new RoomDAO().getAllRooms();
            for (Room r : rooms) {
                Object[] row = {
                    r.getId(),
                    r.getHotelId(),
                    r.getRoomNumber(),
                    r.getType(),
                    r.getPrice(),
                    r.getStatus()
                };
                tableModel.addRow(row);
            }
        });

        // 🔹 Update Room
        updateButton.addActionListener(e -> {
            int row = roomTable.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) tableModel.getValueAt(row, 0);
                    int hotelId = Integer.parseInt(hotelIdField.getText());
                    String roomNo = roomNumberField.getText();
                    String type = typeField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    String status = statusField.getText();

                    Room updatedRoom = new Room(id, hotelId, roomNo, type, price, status);
                    new RoomDAO().updateRoom(updatedRoom);
                    JOptionPane.showMessageDialog(this, "✅ Room updated!");
                    viewButton.doClick();
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "❌ Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to update.");
            }
        });

        // 🔹 Delete Room
        deleteButton.addActionListener(e -> {
            int row = roomTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Delete room ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new RoomDAO().deleteRoom(id);
                    tableModel.removeRow(row);
                    JOptionPane.showMessageDialog(this, "✅ Room deleted.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to delete.");
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        hotelIdField.setText("");
        roomNumberField.setText("");
        typeField.setText("");
        priceField.setText("");
        statusField.setText("");
    }

    public static void main(String[] args) {
        new RoomForm();
    }
}

