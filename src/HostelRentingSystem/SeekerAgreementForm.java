package HostelRentingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeekerAgreementForm extends JFrame {
    private JTextArea txtAgreement;
    private JButton btnAgree, btnReject;

    public SeekerAgreementForm() {
        setTitle("Agreement Form");
        setSize(500, 600);
        setResizable(false);   // Disable resizing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Agreement text area (scrollable)
        txtAgreement = new JTextArea();
        //JEditorPane txtAgreement = new JEditorPane();
        
        //txtAgreement.setContentType("text/html");
        txtAgreement.setText(
        		"Agreement Rules:\n\n" +
        		        "1. Owner must provide valid hostel information.\n" +
        		        "2. Owner must keep room details accurate.\n" +
        		        "3. Payments and renting records must be truthful.\n" +
        		        "4. Owner agrees to follow system rules.\n\n" +
        		        "Please read carefully before proceeding."
        );
        txtAgreement.setWrapStyleWord(true);
        txtAgreement.setLineWrap(true);
      
        txtAgreement.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtAgreement);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        
	  //for button  Default colors
  		Color defaultBg = new Color(0, 120, 215);
  		Color defaultFg = Color.WHITE;

  		//for button  Hover colors
  		Color hoverBg = Color.decode("#f0f0f0");
  		Color hoverFg = new Color(0, 120, 215);
  		
        JPanel buttonPanel = new JPanel();
        btnAgree = new JButton("Agree");
        
        btnAgree.setOpaque(true);                      // allow background painting
        btnAgree.setContentAreaFilled(true);           // MUST be true to fill background
        btnAgree.setBorderPainted(false);              // optional: remove border
        btnAgree.setFocusPainted(false);               // optional: remove focus outline
        btnAgree.setBackground(new Color(0, 120, 215));  // green
        btnAgree.setForeground(Color.WHITE);
        
        //Color Action
        btnAgree.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnAgree.setBackground(hoverBg);    // Change background
		    	btnAgree.setForeground(hoverFg);    // Change text color
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnAgree.setBackground(defaultBg);  // Reset background
		    	btnAgree.setForeground(defaultFg);  // Reset text color
		    }
		});
        
        btnReject = new JButton("Reject");
        
        btnReject.setOpaque(true);                      // allow background painting
        btnReject.setContentAreaFilled(true);           // MUST be true to fill background
        btnReject.setBorderPainted(false);              // optional: remove border
        btnReject.setFocusPainted(false);               // optional: remove focus outline
        btnReject.setBackground(new Color(0, 120, 215));  // green
        btnReject.setForeground(Color.WHITE);
        
        //Color Action
        btnReject.addMouseListener(new MouseAdapter() {
		    
		    public void mouseEntered(MouseEvent e) {
		    	btnReject.setBackground(hoverBg);    // Change background
		    	btnReject.setForeground(hoverFg);    // Change text color
		    }

		    public void mouseExited(MouseEvent e) {
		    	btnReject.setBackground(defaultBg);  // Reset background
		    	btnReject.setForeground(defaultFg);  // Reset text color
		    }
		});


        buttonPanel.add(btnAgree);
        buttonPanel.add(btnReject);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        btnAgree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Seeker Registration form
//            	UserRegistration seeker = new UserRegistration("2");
//            	seeker.setVisible(true);
//                dispose(); // close Agreement form
                
                if(JOptionPane.showConfirmDialog(null, "I Agree to the Terms and Condition.","Confirm Agreement Rule",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                	UserRegistration seeker = new UserRegistration("2");
                	seeker.setVisible(true);
                    dispose(); // close Agreement form
				}
                
            }
        });

        btnReject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to RegisterTypeForm
                RegisterType registerType = new RegisterType();
                registerType.setVisible(true);
                dispose(); // close Agreement form
            }
        });
    }
}

