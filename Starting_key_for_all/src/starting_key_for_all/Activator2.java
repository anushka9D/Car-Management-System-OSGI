package starting_key_for_all;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import anushka_usermanagement_consumer.Activator;
import ashandth_drivermanagement_consumer.DriverConsumerActivator;
import olivea_payment_consumer.PaymentConsumerActivator;
import all_main_consumer.CarManagementActivator;

public class Activator2 implements BundleActivator {

    DriverConsumerActivator driver = new DriverConsumerActivator();
    Activator user = new Activator();
    PaymentConsumerActivator payment = new PaymentConsumerActivator();
    CarManagementActivator vehicle = new CarManagementActivator();

    public void start(BundleContext bundleContext) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // ASCII Art Logo for Driver Management System
        String logo = ""
            + "  _____  _           _       _____           _   _             \n"
            + " |  __ \\| |         (_)     |  __ \\         | | (_)            \n"
            + " | |  | | |_   _ ___ _ _ __ | |__) | __ ___ | |_ _  ___  _ __  \n"
            + " | |  | | | | | / __| | '_ \\|  ___/ '__/ _ \\| __| |/ _ \\| '_ \\ \n"
            + " | |__| | | |_| \\__ \\ | | | | |   | | | (_) | |_| | (_) | | | |\n"
            + " |_____/|_|\\__,_|___/_|_| |_|_|   |_|  \\___/ \\__|_|\\___/|_| |_|\n"
            + "                                                                \n"
            + "  _____           _   _             _____                       \n"
            + " |  __ \\         | | (_)           |  __ \\                      \n"
            + " | |__) | __ ___ | |_ _  ___  _ __ | |__) | __ ___  ___  _ __   \n"
            + " |  ___/ '__/ _ \\| __| |/ _ \\| '_ \\|  ___/ '__/ _ \\/ _ \\| '_ \\  \n"
            + " | |   | | | (_) | |_| | (_) | | | | |   | | |  __/  __/| | | | \n"
            + " |_|   |_|  \\___/ \\__|_|\\___/|_| |_|_|   |_|  \\___|\\___||_| |_| \n"
            + "                                                                \n";

        System.out.println(logo);

        while (running) {
            System.out.println("\t\t\t(¯`·._.··¸.-~*´¨¯¨`*·~-.SELECT AN ACTION.-~*´¨¯¨`*·~-.¸··._.·´¯)");
            System.out.println("");
            System.out.println("\t\t\t\t\t   1. Driver Management");
            System.out.println("\t\t\t\t\t   2. User Management");
            System.out.println("\t\t\t\t\t   3. Payment Management");
            System.out.println("\t\t\t\t\t   4. Vehicle Management");
            System.out.println("\t\t\t\t\t   5. Exit");
            System.out.println("\t\t\t----------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        driver.start(bundleContext); // Start Driver Management
                        break;
                    case 2:
                        user.start(bundleContext); // Start User Management
                        break;
                    case 3:
                        payment.start(bundleContext); // Start Payment Management
                        break;
                    case 4:
                        vehicle.start(bundleContext); // Start Vehicle Management
                        break;
                    case 5:
                        running = false; // Exit the program
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Please enter a valid choice.");
                scanner.nextLine(); // Consume newline
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Driver Management System stopped. Goodbye!");
    }
}