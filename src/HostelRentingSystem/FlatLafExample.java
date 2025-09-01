package HostelRentingSystem;

import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class FlatLafExample {
    public static void main(String[] args) {
        try {
            // Apply FlatLaf theme
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to set FlatLaf look and feel");
        }

        // Create a sample frame
        JFrame frame = new JFrame("FlatLaf in Eclipse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Name:"));
        panel.add(new JTextField(20));

        panel.add(new JLabel("Email:"));
        panel.add(new JTextField(20));

        JButton updateButton = new JButton("Update");
        panel.add(updateButton);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}

