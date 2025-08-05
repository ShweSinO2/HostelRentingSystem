package HostelRentingSystem;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.SwingUtilities;

class Hostel {
	
	private String hostelName,roomNo,address,genderType,ownerName,ownerPhone,roomid,imageUrl,fileName,description; 
	private int price;
	
	public Hostel() {
		this.hostelName = null;
		this.roomNo = null;
		this.address = null;
		this.genderType = null;
		this.ownerName = null;
		this.ownerPhone = null;
		this.roomid = null;
		this.price = 0;
		this.imageUrl = null;
		this.fileName = null;
	}
	
    public Hostel(String hostelName,String roomNo,String address,String gendertype,String ownerName,String ownerPhone,int price,String roomid, String imageUrl, String fileName) {
        this.hostelName = hostelName;
        this.roomNo = roomNo;
        this.address = address;
        this.genderType = gendertype;
        this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.roomid = roomid;
        this.price = price;
        this.imageUrl = imageUrl;
        this.fileName = fileName;
    }

    public String getHostelName() {
    	return hostelName;
    }
    
    public void setHostelName(String name) {
    	this.hostelName = name;
    }
    
    public String getRoomNo() {
    	return roomNo;
    }
    
    public void setRoomNo(String roomNumber) {
    	this.roomNo = roomNumber;
    }
    
    public String getAddress() {
    	return address;
    }
    
    public void setAddress(String add) {
    	this.address = add;
    }
    
    public String getGenderType() {
    	return genderType;
    }
    
    public void setGenderType(String gender) {
    	this.genderType = gender;
    }
    
    public int getPrice() {
    	return price;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
    
    public String getOwnerName() {
    	return ownerName;
    }
    
    public void setOwnerName(String ownername) {
    	this.ownerName = ownername;
    }
    
    public String getOwnerPhone() {
    	return ownerPhone;
    }
    
    public void setOwnerPhone(String ownerPhone) {
    	this.ownerPhone = ownerPhone;
    }
    
    public String getRoomId() {
    	return roomid;
    }
    
    public void setRoomId(String roomid) {
    	this.roomid = roomid;
    }
    
    public String getImageUrl() {
    	return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    
    public String getFileName() {
    	return fileName;
    }
    
    public void setFileName(String fileName) {
    	this.fileName = fileName;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    @Override
    public String toString() {
    	NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    	String formattedPrice = numberFormat.format(price);
        String imagePath = (imageUrl != null && !imageUrl.isEmpty()) ? imageUrl : "uploaded_images/no-image.jpg"; 
        String imageHtmlSrc = "file:///" + new java.io.File(imagePath).getAbsolutePath().replace("\\", "/");
    	return "<html>" +
        "<h4>Hostel Name = " + (hostelName != null ? hostelName : "N/A") + "</h4>" +
        "<h4>Room No = " + (roomNo != null ? roomNo : "N/A") + "</h4>" +
        "<h4>Address = " + (address != null ? address : "N/A") + "</h4>" +
        "<h4>Price = " + formattedPrice + " Kyats</h4>" +
        "<h4>Image <br><br> <img src='" + imageHtmlSrc + "' width='120' height='120'></h4>" +
        "</html>";
    }
}
