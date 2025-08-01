package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.awt.event.ActionEvent;


public class RoomRegistration extends JFrame {
	SqlQuery sqlquery = new SqlQuery();
	ArrayList<JTextField> txtRoomArray = new ArrayList<JTextField>();
	ArrayList<JTextField> txtPriceArray = new ArrayList<JTextField>();
	ArrayList<JTextArea> txtDescriptionArray = new ArrayList<JTextArea>();
	ArrayList<JLabel> lblImagePreviewArray = new ArrayList<>(); 
	ArrayList<JButton> btnImageUploadArray = new ArrayList<>();
	ArrayList<String> imagePathStorage = new ArrayList<>();
	
	private JTextField txtRoomNo;
	private JTextField txtPrice;
	private JTextArea txtDescription;
	
	// RoomModel objects တွေကို သိမ်းဖို့ Array အသစ်
	  private ArrayList<String[]> roomDataList = new ArrayList<>();

    private static final String UPLOAD_DIR = "uploaded_images"; 
    private static final int IMAGE_PREVIEW_SIZE = 50; 
    
	boolean isValid = false;
	boolean isDuplicate = false;
	
	public RoomRegistration(String hostelName,String buildingNo,String roomNo,String roomCount,String state,String city,String street,String gender,String userId) {
		JFrame frame = new JFrame("Room Registration");
//        JPanel panel = new JPanel(new GridLayout(0, 4,10,10));
		JPanel panel = new JPanel(new GridLayout(0, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        int count = Integer.parseInt(roomCount);
        
        for(int j=0;j<count;j++) {
        	txtRoomNo = new JTextField();
        	txtPrice = new JTextField();
        	txtDescription = new JTextArea();
        	JScrollPane descScrollPane = new JScrollPane(txtDescription);
        	
        	JButton btnUploadImage = new JButton("Choose Image");
            JLabel lblImagePreview = new JLabel("No Image", SwingConstants.CENTER);
            lblImagePreview.setPreferredSize(new Dimension(IMAGE_PREVIEW_SIZE, IMAGE_PREVIEW_SIZE));
            lblImagePreview.setBorder(BorderFactory.createEtchedBorder());
//            lblImagePreview.setToolTipText("Click 'Choose Image' to upload");
            
            String[] roomData = new String[5];
            roomDataList.add(roomData);
            
            // ပုံ path ကိုသိမ်းဖို့ String Array ထဲမှာ နေရာပေးထားပါမယ်
            imagePathStorage.add("");
            
            final String[] imageInfo = new String[2]; // [path, fileName]
            imageInfo[0] = "";
            imageInfo[1] = "";
            
            final int currentIndex = j; // For use inside anonymous class
            btnUploadImage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Select Image for Room " + (currentIndex + 1));
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                            "Image Files", "jpg", "jpeg", "png", "gif"));

                    int userSelection = fileChooser.showOpenDialog(RoomRegistration.this);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        if (selectedFile != null) {
                            try {
                                // ပုံကို upload folder ထဲကို ကူးမယ်
                                File uploadFolder = new File(UPLOAD_DIR);
                                if (!uploadFolder.exists()) {
                                    uploadFolder.mkdirs();
                                }
                                
                                // ပုံကို သိမ်းဖို့ ထူးခြားတဲ့နာမည်ပေး
                                String originalFileName = selectedFile.getName();
//                                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                                String fileExtension = "";
                                int dotIndex = originalFileName.lastIndexOf(".");
                                if (dotIndex > 0 && dotIndex < originalFileName.length() - 1) {
                                    fileExtension = originalFileName.substring(dotIndex);
                                }
                                String newUniqueFileName = UUID.randomUUID().toString() + fileExtension;
                                
                                File destinationFile = new File(uploadFolder.getAbsolutePath() + File.separator + newUniqueFileName);
                                Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                // ရွေးလိုက်တဲ့ပုံကို JLabel မှာ ပြသပါမယ်
                                ImageIcon originalIcon = new ImageIcon(destinationFile.getAbsolutePath());
                                Image image = originalIcon.getImage();
                                Image scaledImage = image.getScaledInstance(IMAGE_PREVIEW_SIZE, IMAGE_PREVIEW_SIZE, Image.SCALE_SMOOTH);
                                lblImagePreviewArray.get(currentIndex).setIcon(new ImageIcon(scaledImage));
                                lblImagePreviewArray.get(currentIndex).setText(""); // Icon ပြပြီးသားဖြစ်လို့ စာသားဖျက်
                                lblImagePreviewArray.get(currentIndex).setToolTipText(originalFileName);
                                
                                String relativeImagePath = UPLOAD_DIR + "/" + File.separator + newUniqueFileName;
                                roomDataList.get(currentIndex)[3] = relativeImagePath; // image_url ကို relative path အဖြစ်သိမ်း
                                roomDataList.get(currentIndex)[4] = newUniqueFileName;
                                
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(RoomRegistration.this,
                                        "Error uploading image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            });

        	txtRoomArray.add(txtRoomNo);
        	txtPriceArray.add(txtPrice);
        	txtDescriptionArray.add(txtDescription);
        	btnImageUploadArray.add(btnUploadImage);
        	lblImagePreviewArray.add(lblImagePreview);
        	
        	// Loop အစမှာ RoomModel အသစ်တစ်ခု ဖန်တီးပြီး ArrayList ထဲ ထည့်ထားပါမယ်
//            roomDataList.add(new RoomModel("", "", "", "", ""));
        	
    	   File uploadFolder = new File(UPLOAD_DIR);
           if (!uploadFolder.exists()) {
               uploadFolder.mkdirs();
           }
        }
        
        for(int i=0;i<count;i++) {
        	JLabel lblNewLabel = new JLabel("Room No:");
    		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    		lblNewLabel.setPreferredSize(new Dimension(140, 30));
    		panel.add(lblNewLabel);
    		panel.add(txtRoomArray.get(i));
    		
    		JLabel lblNewLabel_1 = new JLabel("Price:");
    		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
    		panel.add(lblNewLabel_1);
    		panel.add(txtPriceArray.get(i));
    		
    		JLabel lblDescription = new JLabel("Description:");
    		lblDescription.setFont(new Font("Arial", Font.PLAIN, 12));
    		panel.add(lblDescription);
    		panel.add(txtDescriptionArray.get(i));
    		
    		 // Image Upload Button
            panel.add(btnImageUploadArray.get(i));
            
            // Image Preview (ပုံလေးပြမယ့် JLabel)
            panel.add(lblImagePreviewArray.get(i));
        }
        
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String[] hostelData = new String[9];
					String[] roomData = new String[3];
					
					hostelData[0] = hostelName;
					hostelData[1] = buildingNo;
					hostelData[2] = roomNo;
					hostelData[3] = roomCount;
					hostelData[4] = state;
					hostelData[5] = city;
					hostelData[6] = street;
					hostelData[7] = userId;
					hostelData[8] = gender;
										
					String[][] roomList = new String[count][5];
					// check validation
					for(int k=0;k<count;k++) {
						String roomNo = txtRoomArray.get(k).getText();
						String price = txtPriceArray.get(k).getText();
						String description = txtDescriptionArray.get(k).getText();
						
						roomDataList.get(k)[0] = roomNo;
		                roomDataList.get(k)[1] = price;
		                roomDataList.get(k)[2] = description;
		                    
		                    
						String image_path = imagePathStorage.get(k);
						System.out.println("roomNo => "+ roomNo + "\tprice" + price);
						System.out.println("desc => "+ description + "\tpath" + image_path);
						
						if(Checking.IsNull(roomNo) || Checking.IsLetter(roomNo)) {
							JOptionPane.showMessageDialog(null, "You must enter valid Room Number");
							isValid = false;
							break;
						} else {
							isValid = true;
						}
						
						if(Checking.IsNull(price) || !Checking.IsAllDigit(price) || Integer.parseInt(price)==0) {
							JOptionPane.showMessageDialog(null, "You must enter valid Price");
							isValid = false;
							break;
						} else if(!Checking.IsValidPrice(price)) {
							JOptionPane.showMessageDialog(null, "Hostel Fee is Expensive!!");
							isValid = false;
							break;
						} else {
							isValid = true;
						}
						
									
					}
					
					System.out.println("Room Validate Outside => "+isValid);
					
					// check duplicate room
					if(isValid) {
						for(int k=0;k<count - 1;k++) {
							String roomNo = txtRoomArray.get(k).getText();
							
							for(int j=k+1;j<count;j++) {
								String nextRoomNo = txtRoomArray.get(j).getText();
								if(roomNo.equals(nextRoomNo)) {
									JOptionPane.showMessageDialog(null, "Duplicate Room Number!!");
									isDuplicate = true;
									break;
								}
								else {
									isDuplicate = false;
								}	
							}
							if(isDuplicate) {
								break;
							}
								
						}
						System.out.println("Room Duplicate Outside => "+isDuplicate);
					}
					
					
					// insert data
					if(isValid && !isDuplicate) {
						System.out.println("Room Validate Inside => "+isValid);
						System.out.println("Room Duplicate Inside => "+isDuplicate);
						
						boolean save = sqlquery.insertData("hostel", hostelData);
						String hostelId = sqlquery.getId("hostel");
						System.out.println("Hostel ID => "+hostelId);
						
						for(int k=0;k<count;k++) {
//							String roomNo = txtRoomArray.get(k).getText();
//							String price = txtPriceArray.get(k).getText();
						    String[] dataToInsert = roomDataList.get(k);
                            String room_no = dataToInsert[0];
                            String price = dataToInsert[1];
                            String description = dataToInsert[2];
                            String image_url = dataToInsert[3];
                            String file_name = dataToInsert[4];
                            
                            String[] roomDataWithHostelId = {room_no, price, hostelId, description, image_url, file_name};

//							String[] room = {roomNo, price, hostelId}; // ["Room No", "Price", "HostelID"]
							roomList[k] = roomDataWithHostelId;  
						}
						
						if(save) {
						// ["Room No", "Price", "HostelID"]
						// String[][] == new 
						// [
						// 	["Room No", "Price", "HostelID"],
						//  ["Room No", "Price", "HostelID"],
						//  ["Room No", "Price", "HostelID"]
						// ]
						
						System.out.println("Room List => "+Arrays.toString(roomList));
						for(String[] data: roomList) {
							System.out.println("Room Data 1 =>" + data[0]);
							System.out.println("Room Data 2 =>" + data[1]);
							System.out.println("Room Data 3 =>" + data[2]);
							System.out.println("Room Data 4 =>" + data[3]);
							System.out.println("Room Data 5 =>" + data[4]);
							System.out.println("Room Data 6 =>" + data[5]);
							System.out.println("----------------------------------");

							sqlquery.insertData("room",data);
						}
						JOptionPane.showMessageDialog(null, "Successfully Saved Room data");
						
						// clean text box
						for(int k=0;k<count;k++) {
							txtRoomArray.get(k).setText("");
							txtPriceArray.get(k).setText("");
						}	
						frame.setVisible(false);
					}
				}	
				
			}
		});
		
        panel.add(btnConfirm);
       
        frame.getContentPane().add(panel);
        frame.setResizable(true);
        frame.setBounds(380, 180, 550, 500);
        frame.pack();
        frame.setVisible(true);
	}
	
}

