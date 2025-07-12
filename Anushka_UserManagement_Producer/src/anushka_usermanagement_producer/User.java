package anushka_usermanagement_producer;

public class User {
	
	private String UID;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String userType;
    private String associatedVehicle;
    
    public User(String UID, String name, String email, String contact, String address, String userType, String associatedVehicles) {
        this.UID = UID;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.userType = userType;
        this.associatedVehicle = associatedVehicles;
    }
    
    public String getUserID() {
        return UID;
    }
    
    public void setUserID(String userID) {
        this.UID = userID;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    public String getAssociatedVehicles() {
        return associatedVehicle;
    }
    
    public void setAssociatedVehicles(String vehicle) {
        this.associatedVehicle = vehicle;
    }
    

}
