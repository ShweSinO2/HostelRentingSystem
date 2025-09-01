package HostelRentingSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OwnerAgreementForm extends JFrame {
    private JTextArea txtAgreement;
    private JButton btnAgree, btnReject;

    public OwnerAgreementForm() {
        setTitle("");
        setSize(400, 300);
        setResizable(false);   // Disable resizing
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Agreement text area (scrollable)
        txtAgreement = new JTextArea();
        JEditorPane txtAgreement = new JEditorPane();
        
        txtAgreement.setContentType("text/html");
        txtAgreement.setText(
        		"<html>" +
        		        "<h1 style='color:blue; text-align:center;'>Agreement Rules</h1>" +
        		        "<p style='font-size:12px; color:blue;'>Verification of Correct Information</p>" +
        		        "<p style='color:gray;'>'I guarantee that all my personal information and the submitted hostel details are accurate. I agree to be subject to legal action under applicable laws if the information provided is found to be false.'</p>" +
        		        "<p style='font-size:12px; color:blue;'>Submission of Supporting Documents</p>" +
        		        "<p style='color:gray;'>'As a hostel owner, I agree to provide documents related to my property ownership (e.g., ID card, ownership deed, etc.) when request by the System.'</p>" +
        		        "<p style='font-size:12px; color:blue;'>Request for Consent</p>" +
        		        "<p style='color:gray;'>'As the hostel owner, I consent to the provided hostel information being displayed to prospective seekers and, if necessary, shared with relevant legal authorities.'</p>" +
        		        "<p style='font-size:12px; color:blue;'>Responsibilities and Terms</p>" +
        		        "<p style='color:gray;'>'I will be fully responsible for the safety, cleanliness, and quality of service for the residents in my hostel.'</p>" +
        		        "<p style='color:gray;'>'I will clearly and transparently list the room rates, service fees, and other relevant information'</p>" +
        		        "<p style='font-size:12px; color:blue;'>Service Fees</p>" +
        		        "<p style='color:gray;'>'Hostel owners must pay a 5% service fee for each rental transaction.'</p>" +
        		        "<p style='font-size:12px; color:blue;'>Compliance with Laws</p>" +
        		        "<p style='color:gray;'>'The hostel owner will comply with all local laws, licenses, and regulations.'</p>" +
        		        "<p style='font-size:8px; color:red;' font-style:italic;'>I agree to the above terms..</p>" +
            		    
        		        "</html>"
        );
        //txtAgreement.setWrapStyleWord(true);
        //txtAgreement.setLineWrap(true);
      
        txtAgreement.setEditable(false);
        
     // force scroll position to top
        txtAgreement.setCaretPosition(0);

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
                // Open Owner Registration form
//                UserRegistration owner = new UserRegistration("3");
//                owner.setVisible(true);
//                dispose(); // close Agreement form
                
                if(JOptionPane.showConfirmDialog(null, "I Agree to the Terms and Condition.","Confirm Agreement Rule",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                	UserRegistration owner = new UserRegistration("3");
                    owner.setVisible(true);
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
