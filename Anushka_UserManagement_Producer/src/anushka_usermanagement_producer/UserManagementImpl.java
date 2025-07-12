package anushka_usermanagement_producer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManagementImpl implements UserManagement {
	
	private static final String FILE_NAME = ".\\users.txt";
    private Scanner scan;
    private List<User> registeredUsers;
    
    public UserManagementImpl() {
        scan = new Scanner(System.in);
        registeredUsers = new ArrayList<>();
        loadUsersFromFile();
    }
    
    private void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_NAME)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String userID = parts[0];
                    String name = parts[1];
                    String email = parts[2];
                    String contact = parts[3];
                    String address = parts[4];
                    String userType = parts[5];
                    
                    String associatedVehicle = "";
                    if (parts.length > 6) {
                        associatedVehicle = parts[6];
                    }
                    
                    User user = new User(userID, name, email, contact, address, userType, associatedVehicle);
                    registeredUsers.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
            
            try {
                File file = new File(FILE_NAME);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException ex) {
                System.err.println("Error creating users file: " + ex.getMessage());
            }
        }
    }
    
    private void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (User user : registeredUsers) {
                writer.write(user.getUserID() + "," + user.getName() + "," + user.getEmail() + "," + 
                             user.getContact() + "," + user.getAddress() + "," + user.getUserType() + 
                             "," + user.getAssociatedVehicles() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }
    }
    
 // -------------------------- function -------------------------------------------
    
    @Override
    public void insertUserDetails() {
        System.out.println("\n------- Register New User To System --------");
        System.out.print("Enter User ID: ");
        String userID = scan.nextLine();
        
        // Check if user already exists
        if (findUserById(userID) != null) {
            System.out.println("User with ID " + userID + " already exists.");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Email: ");
        String email = scan.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = scan.nextLine();
        System.out.print("Enter Address: ");
        String address = scan.nextLine();
        System.out.print("Enter User Type (Vip user/Normal user): ");
        String userType = scan.nextLine();
        System.out.print("Enter Associated Vehicle (or leave blank): ");
        String vehicle = scan.nextLine();
        
        User newUser = new User(userID, name, email, contact, address, userType, vehicle);
        registeredUsers.add(newUser);
        saveUsersToFile();
        
        System.out.println("User with ID " + userID + " added Successfully.");
    }

	@Override
    public void displayUsers() {
        if (registeredUsers.isEmpty()) {
            System.out.println("No users registered in the system.");
            return;
        }
        
        System.out.printf("%-10s %-20s %-25s %-15s %-20s %-10s %-15s%n", 
                         "User ID", "Name", "Email", "Contact", "Address", "Type", "Vehicle");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        
        for (User user : registeredUsers) {
            System.out.printf("%-10s %-20s %-25s %-15s %-20s %-10s %-15s%n", 
                             user.getUserID(), user.getName(), user.getEmail(), user.getContact(), 
                             user.getAddress(), user.getUserType(), user.getAssociatedVehicles());
        }
    }
	
    @Override
    public List<User> getAllUsers() {
        displayUsers();
        return new ArrayList<>(registeredUsers);
    }
    
    
    @Override
    public User searchUserById(String userID) {
        User user = findUserById(userID);
        if (user != null) {
            System.out.println("\nUser Details:");
            System.out.println("ID: " + user.getUserID());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Contact: " + user.getContact());
            System.out.println("Address: " + user.getAddress());
            System.out.println("User Type: " + user.getUserType());
            System.out.println("Associated Vehicle: " + user.getAssociatedVehicles());
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
        return user;
    }
    
    @Override
    public void updateUserDetails(String userID) {
        User userToUpdate = findUserById(userID);
        if (userToUpdate != null) {
            System.out.println("Enter updated details for user with ID " + userID + ":");
            System.out.print("Name (" + userToUpdate.getName() + "): ");
            String name = scan.nextLine();
            if (!name.isEmpty()) {
                userToUpdate.setName(name);
            }
            
            System.out.print("Email (" + userToUpdate.getEmail() + "): ");
            String email = scan.nextLine();
            if (!email.isEmpty()) {
                userToUpdate.setEmail(email);
            }
            
            System.out.print("Contact (" + userToUpdate.getContact() + "): ");
            String contact = scan.nextLine();
            if (!contact.isEmpty()) {
                userToUpdate.setContact(contact);
            }
            
            System.out.print("Address (" + userToUpdate.getAddress() + "): ");
            String address = scan.nextLine();
            if (!address.isEmpty()) {
                userToUpdate.setAddress(address);
            }
            
            System.out.print("User Type (" + userToUpdate.getUserType() + "): ");
            String userType = scan.nextLine();
            if (!userType.isEmpty()) {
                userToUpdate.setUserType(userType);
            }
            
            System.out.print("Associated Vehicle (" + userToUpdate.getAssociatedVehicles() + "): ");
            String vehicle = scan.nextLine();
            if (!vehicle.isEmpty()) {
                userToUpdate.setAssociatedVehicles(vehicle);
            }
            
            saveUsersToFile();
            System.out.println("User details updated successfully.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }
    

    
    @Override
    public void deleteUser(String userID) {
        User userToDelete = findUserById(userID);
        if (userToDelete != null) {
            registeredUsers.remove(userToDelete);
            saveUsersToFile();
            System.out.println("User with ID " + userID + " deleted successfully.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }
    

    
    @Override
    public void associateVehicle(String userID) {
        User user = findUserById(userID);
        if (user != null) {
            System.out.print("Enter Vehicle ID (Vehicle No) to associate with user: ");
            String vehicleID = scan.nextLine();
            
            user.setAssociatedVehicles(vehicleID);
            saveUsersToFile();
            
            System.out.println("Vehicle " + vehicleID + " associated with user " + userID + " successfully.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }
    
    @Override
    public String getUserVehicle(String userID) {
        User user = findUserById(userID);
        if (user != null) {
            String vehicle = user.getAssociatedVehicles();
            
            System.out.println("\nVehicle associated with user " + userID + ":");
            if (vehicle == null || vehicle.isEmpty()) {
                System.out.println("No vehicle associated with this user.");
            } else {
                System.out.println("--------------------");
                System.out.println(vehicle);
                System.out.println("--------------------");
            }
            
            return vehicle;
        } else {
            System.out.println("User with ID " + userID + " not found.");
            return "";
        }
    }
    
    @Override
    public List<User> getUsersByType(String userType) {
        List<User> usersOfType = new ArrayList<>();
        
        for (User user : registeredUsers) {
            if (user.getUserType().equalsIgnoreCase(userType)) {
                usersOfType.add(user);
            }
        }
        
        System.out.printf("%-10s %-20s %-25s %-15s %-20s%n", 
                         "User ID", "Name", "Email", "Contact", "Address");
        System.out.println("------------------------------------------------------------------------------------");
        
        for (User user : usersOfType) {
            System.out.printf("%-10s %-20s %-25s %-15s %-20s%n", 
                             user.getUserID(), user.getName(), user.getEmail(), user.getContact(), user.getAddress());
        }
        
        return usersOfType;
    }
    
    private User findUserById(String userID) {
        for (User user : registeredUsers) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }
	
	
}
