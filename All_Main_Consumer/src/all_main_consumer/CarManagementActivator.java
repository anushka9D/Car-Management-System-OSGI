package all_main_consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import car_producer.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarManagementActivator implements BundleActivator {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private CarService carService;

    // Counters for automatic ID generation
    private int carCount = 1;
    private int vanCount = 1;
    private int bikeCount = 1;

    @Override
    public void start(BundleContext context) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Get the CarService from OSGi service registry
        ServiceReference<CarService> reference = context.getServiceReference(CarService.class);
        if (reference != null) {
            carService = context.getService(reference);
        }

       

        while (running) {
            System.out.println("\n=== Car Management System ===");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Add New Vehicle");
            System.out.println("3. Update Vehicle Details");
            System.out.println("4. Remove a Vehicle");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    if (carService != null) {
                        carService.displayVehicleOptions();  // Call the service method
                    }
                    displayAvailableVehicles();
                    break;
                case 2:
                    addNewVehicle(scanner);
                    break;
                case 3:
                    updateVehicleDetails(scanner);
                    break;
                case 4:
                    removeVehicle(scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting Car Management...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Car Management Module Stopped.");
    }

    

    private void displayAvailableVehicles() {
        System.out.println("\nAvailable Vehicles:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Type", "Seats", "Price ($)");
        System.out.println("---------------------------------------------------------");
        for (Vehicle v : vehicleList) {
            System.out.printf("%-10s %-20s %-10d %-10.2f%n", v.getId(), v.getType(), v.getSeats(), v.getPrice());
        }
        System.out.println("---------------------------------------------------------");
    }

    private void addNewVehicle(Scanner scanner) {
    	System.out.println("  --------------------------------------------------  ");
    	System.out.println(" |   _____  ______          _____  ______  _______  | ");
    	System.out.println(" |  / ____| |  ____| |    / ____| |  ___| |__   __| | ");
    	System.out.println(" | | (___   | |__  | |    | |___  |  |       | |    | ");
    	System.out.println(" |  \\__\\  |  __| | |    | |___| |  |       | |    | ");
    	System.out.println(" |  ____) | | |____| |____| |____ |  |__     | |    | ");
    	System.out.println(" | |_____/  |______|_|____\\_____||_____|    |_|    | ");
    	System.out.println(" |                                                  | ");
    	System.out.println("  --------------------------------------------------  ");
    	System.out.println("  --------------------------------------------------  ");
    	System.out.println(" |                  V E H I C L E                   | ");
    	System.out.println("  --------------------------------------------------- ");
        System.out.print("Enter Vehicle Type (Car/Van/Bike): ");
        String type = scanner.nextLine().toLowerCase();
        System.out.print("Enter Model Name: ");
        String model = scanner.nextLine();
        System.out.print("Enter Seating Capacity: ");
        int seats = scanner.nextInt();
        System.out.print("Enter Price per Day: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        String id;
        Vehicle newVehicle;

        switch (type) {
            case "car":
                id = "C" + String.format("%03d", carCount++);
                newVehicle = new Car(id, model, seats, price);
                break;
            case "van":
                id = "V" + String.format("%03d", vanCount++);
                newVehicle = new Van(id, model, seats, price);
                break;
            case "bike":
                id = "B" + String.format("%03d", bikeCount++);
                newVehicle = new Bike(id, model, seats, price);
                break;
            default:
                System.out.println("Invalid vehicle type.");
                return;
        }

        vehicleList.add(newVehicle);
        System.out.println("Vehicle added successfully! Assigned ID: " + id);
    }

    private void updateVehicleDetails(Scanner scanner) {
        System.out.print("Enter Vehicle ID to update: ");
        String id = scanner.nextLine();
        for (Vehicle v : vehicleList) {
            if (v.getId().equals(id)) {
                System.out.print("Enter new price per day: ");
                double newPrice = scanner.nextDouble();
                v.setPrice(newPrice);
                System.out.println("Vehicle details updated successfully!");
                return;
            }
        }
        System.out.println("Vehicle not found!");
    }

    private void removeVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID to remove: ");
        String id = scanner.nextLine();
        if (vehicleList.removeIf(v -> v.getId().equals(id))) {
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle not found!");
        }
    }
}
