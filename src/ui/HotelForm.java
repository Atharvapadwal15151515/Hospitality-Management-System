package ui;

import dao.HotelDAO;
import model.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

@SuppressWarnings({ "unused", "serial" })
public class HotelForm extends JFrame {
    private JTextField nameField, locationField, amenitiesField;
    private JTable hotelTable;
    private DefaultTableModel tableModel;

    public HotelForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Hotel Management");
        setSize(700, 500);
        setLocationRelativeTo(null); // center screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // 🔹 Input Panel (GridBag)
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        locationField = new JTextField(15);
        amenitiesField = new JTextField(15);

        JLabel nameLabel = new JLabel("Hotel Name:");
        JLabel locationLabel = new JLabel("Location:");
        JLabel amenitiesLabel = new JLabel("Amenities:");

        nameLabel.setFont(font);
        locationLabel.setFont(font);
        amenitiesLabel.setFont(font);
        nameField.setFont(font);
        locationField.setFont(font);
        amenitiesField.setFont(font);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(locationField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(amenitiesLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(amenitiesField, gbc);

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

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Table
        String[] columns = {"ID", "Name", "Location", "Amenities"};
        tableModel = new DefaultTableModel(columns, 0);
        hotelTable = new JTable(tableModel);
        hotelTable.setFont(font);
        hotelTable.setRowHeight(22);
        add(new JScrollPane(hotelTable), BorderLayout.CENTER);

        // 🔹 Add Hotel
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String location = locationField.getText();
            String amenities = amenitiesField.getText();

            if (name.isEmpty() || location.isEmpty() || amenities.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
                return;
            }

            Hotel h = new Hotel(name, location, amenities);
            new HotelDAO().addHotel(h);
            JOptionPane.showMessageDialog(this, "✅ Hotel added!");
            clearFields();
        });

        // 🔹 View Hotels
        viewButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            List<Hotel> hotels = new HotelDAO().getAllHotels();
            for (Hotel h : hotels) {
                Object[] row = {
                    h.getId(),
                    h.getName(),
                    h.getLocation(),
                    h.getAmenities()
                };
                tableModel.addRow(row);
            }
        });

        // 🔹 Update Hotel
        updateButton.addActionListener(e -> {
            int row = hotelTable.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) tableModel.getValueAt(row, 0);
                    String name = nameField.getText();
                    String location = locationField.getText();
                    String amenities = amenitiesField.getText();

                    if (name.isEmpty() || location.isEmpty() || amenities.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "❌ Fill all fields before updating.");
                        return;
                    }

                    Hotel updated = new Hotel(id, name, location, amenities);
                    new HotelDAO().updateHotel(updated);
                    JOptionPane.showMessageDialog(this, "✅ Hotel updated!");
                    viewButton.doClick();
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "❌ Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to update.");
            }
        });

        // 🔹 Delete Hotel
        deleteButton.addActionListener(e -> {
            int row = hotelTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Delete hotel ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new HotelDAO().deleteHotel(id);
                    tableModel.removeRow(row);
                    JOptionPane.showMessageDialog(this, "✅ Hotel deleted.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to delete.");
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        locationField.setText("");
        amenitiesField.setText("");
    }

    public static void main(String[] args) {
        new HotelForm();
    }
}

