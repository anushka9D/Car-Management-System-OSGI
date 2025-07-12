package ashandth_drivermanagement_consumer;

import ashandth_drivermanagement_producer.DriverService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class DriverConsumerActivator implements BundleActivator {
    private BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        System.out.println("Driver Consumer Bundle Started");

        // Start the main logic
        startDriverConsumer();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Driver Consumer Bundle Stopped");
    }

    private void startDriverConsumer() {
        ServiceReference<DriverService> serviceReference = context.getServiceReference(DriverService.class);
        if (serviceReference != null) {
            DriverService driverService = context.getService(serviceReference);
            Scanner scanner = new Scanner(System.in);
            while (true) {
            	
            	System.out.println("\n");
                System.out.println("1. Register New Driver");
                System.out.println("2. View All Drivers");
                System.out.println("3. Search Driver by ID");
                System.out.println("4. Update Driver Details");
                System.out.println("5. Delete Driver");
                System.out.println("6. Check Vehicle Driven");
                System.out.println("7. Check Customer Traveled");
                System.out.println("8. Check Salary with Payment ID");       
                System.out.println("9. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter License Number: ");
                        String licenseNumber = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter NIC: ");
                        String nic = scanner.nextLine();
                        System.out.print("Enter Vehicle Type (Car or Van or Bike): ");
                        String vehicleType = scanner.nextLine();
                        System.out.println(driverService.registerDriver(name, licenseNumber, phoneNumber, nic, vehicleType));
                        break;
                    case 2:
                        System.out.println(driverService.viewAllDrivers());
                        break;
                    case 3:
                        System.out.print("Enter Driver ID: ");
                        String did = scanner.nextLine();
                        System.out.println(driverService.searchDriverById(did));
                        break;
                    case 4:
                        System.out.print("Enter Driver ID: ");
                        did = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        name = scanner.nextLine();
                        System.out.print("Enter License Number: ");
                        licenseNumber = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        phoneNumber = scanner.nextLine();
                        System.out.print("Enter NIC: ");
                        nic = scanner.nextLine();
                        System.out.print("Enter Vehicle Type (Car or Van or Bike: ");
                        vehicleType = scanner.nextLine();
                        System.out.println(driverService.updateDriverDetails(did, name, licenseNumber, phoneNumber, nic, vehicleType));
                        break;
                    case 5:
                        System.out.print("Enter Driver ID: ");
                        did = scanner.nextLine();
                        System.out.println(driverService.deleteDriver(did));
                        break;
                    case 6:
                        System.out.print("Enter Driver ID: ");
                        did = scanner.nextLine();
                        System.out.println(driverService.checkVehicleDriven(did));
                        break;
                    case 7:
                        System.out.print("Enter Driver ID: ");
                        did = scanner.nextLine();
                        System.out.println(driverService.checkCustomerTraveled(did));
                        break;
                    case 8:
                        System.out.print("Enter Driver ID: ");
                        did = scanner.nextLine();
                        System.out.print("Enter Payment ID: ");
                        String pid = scanner.nextLine();
                        System.out.println(driverService.checkSalaryWithPaymentId(did, pid));
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } else {
            System.out.println("Driver Service not found");
        }
    }
}