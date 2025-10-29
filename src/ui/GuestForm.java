package ui;

import dao.GuestDAO;
import model.Guest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GuestForm extends JFrame {
    private JTextField nameField, emailField, phoneField;
    private JTable guestTable;
    private DefaultTableModel tableModel;

    public GuestForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        setTitle("Guest Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        // 🔹 Input Panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = new JTextField(15);
        emailField = new JTextField(15);
        phoneField = new JTextField(15);

        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");

        nameLabel.setFont(font);
        emailLabel.setFont(font);
        phoneLabel.setFont(font);
        nameField.setFont(font);
        emailField.setFont(font);
        phoneField.setFont(font);

        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(emailLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(phoneField, gbc);

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
        String[] columns = {"ID", "Name", "Email", "Phone"};
        tableModel = new DefaultTableModel(columns, 0);
        guestTable = new JTable(tableModel);
        guestTable.setFont(font);
        guestTable.setRowHeight(22);
        add(new JScrollPane(guestTable), BorderLayout.CENTER);

        // 🔹 Add Guest
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "❌ Please fill all fields.");
                return;
            }

            Guest g = new Guest(name, email, phone);
            new GuestDAO().addGuest(g);
            JOptionPane.showMessageDialog(this, "✅ Guest added!");
            clearFields();
        });

        // 🔹 View Guests
        viewButton.addActionListener(e -> {
            tableModel.setRowCount(0);
            List<Guest> guests = new GuestDAO().getAllGuests();
            for (Guest g : guests) {
                Object[] row = {
                    g.getId(),
                    g.getName(),
                    g.getEmail(),
                    g.getPhone()
                };
                tableModel.addRow(row);
            }
        });

        // 🔹 Update Guest
        updateButton.addActionListener(e -> {
            int row = guestTable.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = (int) tableModel.getValueAt(row, 0);
                    String name = nameField.getText();
                    String email = emailField.getText();
                    String phone = phoneField.getText();

                    if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "❌ Fill all fields to update.");
                        return;
                    }

                    Guest updated = new Guest(id, name, email, phone);
                    new GuestDAO().updateGuest(updated);
                    JOptionPane.showMessageDialog(this, "✅ Guest updated!");
                    viewButton.doClick();
                    clearFields();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "❌ Update failed: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to update.");
            }
        });

        // 🔹 Delete Guest
        deleteButton.addActionListener(e -> {
            int row = guestTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Delete guest ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    new GuestDAO().deleteGuest(id);
                    tableModel.removeRow(row);
                    JOptionPane.showMessageDialog(this, "✅ Guest deleted.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "❌ Select a row to delete.");
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
    }

    public static void main(String[] args) {
        new GuestForm();
    }
}
