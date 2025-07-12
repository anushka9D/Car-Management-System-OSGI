package anushka_usermanagement_producer;

import java.util.List;

public interface UserManagement {
	
	void insertUserDetails();
	
	List<User> getAllUsers();
	
	void displayUsers();
    
	User searchUserById(String userID);
	
	void updateUserDetails(String userID);
    
    void deleteUser(String userID);
    
    void associateVehicle(String userID);
    
    String getUserVehicle(String userID);
    
    List<User> getUsersByType(String userType);
    
    
    
    
    
    
    
    
    
    
    
    

}
