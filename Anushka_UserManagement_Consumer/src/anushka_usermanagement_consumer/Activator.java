package anushka_usermanagement_consumer;

import anushka_usermanagement_producer.UserManagement;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


public class Activator implements BundleActivator {

    private ServiceReference serviceReference;
    private Scanner scan;
    
    public void start(BundleContext context) throws Exception {
    	
        System.out.println("User Details Management Consumer Started");
        serviceReference = context.getServiceReference(UserManagement.class.getName());
        
        @SuppressWarnings("unchecked")
        UserManagement userService = (UserManagement) context.getService(serviceReference);
        
        scan = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--------------User Details Management--------------");
            System.out.println("1. Register New User");
            System.out.println("2. View All Users");
            System.out.println("3. Search User by ID");
            System.out.println("4. Update User Details");
            System.out.println("5. Delete User");
            System.out.println("6. Associate Vehicle with User");
            System.out.println("7. View User's Vehicle");
            System.out.println("8. View Users by Type");
            System.out.println("9. Exit");
            
            System.out.print("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine();
            
            switch (choice) {
                case 1:
                    userService.insertUserDetails();
                    break;
                case 2:
                    userService.getAllUsers();
                    break;
                case 3:
                    System.out.print("Enter User ID to search: ");
                    String searchID = scan.nextLine();
                    userService.searchUserById(searchID);
                    break;
                case 4:
                    System.out.print("Enter User ID to update: ");
                    String updateID = scan.nextLine();
                    userService.updateUserDetails(updateID);
                    break;
                case 5:
                    System.out.print("Enter User ID to delete: ");
                    String deleteID = scan.nextLine();
                    userService.deleteUser(deleteID);
                    break;
                case 6:
                    System.out.print("Enter User ID to associate vehicle: ");
                    String userIDAssoc = scan.nextLine();
                    userService.associateVehicle(userIDAssoc);
                    break;
                case 7:
                    System.out.print("Enter User ID to view vehicle: ");
                    String userIDVehicle = scan.nextLine();
                    userService.getUserVehicle(userIDVehicle);
                    break;
                case 8:
                    System.out.print("Enter User Type to filter (Vip user/Normal user): ");
                    String userType = scan.nextLine();
                    userService.getUsersByType(userType);
                    break;
                case 9:
                    System.out.println("Exiting User Details Management...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    public void stop(BundleContext context) throws Exception {
    	
        System.out.println("User Details Management Consumer Stopped");
        
        context.ungetService(serviceReference);
        
        if (scan != null) {
            scan.close();
        }
    }

}
