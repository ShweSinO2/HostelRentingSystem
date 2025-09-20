package HostelRentingSystem;

class Hostel {
	
	private String hostelName,roomNo,address,genderType,ownerName,ownerPhone,roomid; 
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
	}
	
    public Hostel(String hostelName,String roomNo,String address,String gendertype,String ownerName,String ownerPhone,int price,String roomid) {
        this.hostelName = hostelName;
        this.roomNo = roomNo;
        this.address = address;
        this.genderType = gendertype;
        this.ownerName = ownerName;
		this.ownerPhone = ownerPhone;
		this.roomid = roomid;
        this.price = price;
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
    
    @Override
    public String toString() {
        return "<html><h4>Hostel Name = "+hostelName+"</h4><h4>Room No = "+roomNo+"</h4><h4>Address ="+address+"</h4><h4>Price ="+price+"</h4></html>";
    }
}
