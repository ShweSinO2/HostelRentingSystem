package HostelRentingSystem;

import javax.swing.*;

public class CustomConfirmDialogExample {
   public static void main(String[] args) {
      JTextField textField = new JTextField(10);
      JLabel label = new JLabel("Enter your name:");
      Object[] obj = {label, textField};

      int option = JOptionPane.showConfirmDialog(null, obj, "Enter Name", JOptionPane.OK_CANCEL_OPTION);

      if(option == JOptionPane.OK_OPTION) {
         String name = textField.getText();
         JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
      }
   }
}

