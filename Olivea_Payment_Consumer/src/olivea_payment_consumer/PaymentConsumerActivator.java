package olivea_payment_consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import olivea_payment_producer.Payment;
import olivea_payment_producer.PaymentService;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentConsumerActivator implements BundleActivator {

    private ServiceReference serviceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        serviceReference = context.getServiceReference(PaymentService.class.getName());
         PaymentService paymentService = (PaymentService) context.getService(serviceReference);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Payment System Menu ---");
            System.out.println("1. Add New Payment");
            System.out.println("2. Update Payment Details By PID");
            System.out.println("3. Display Payments Details");
            System.out.println("4. Calculate Total Amount by Vehicle Type[Case Simple]");
            System.out.println("5. Delete Payment by PID");
            System.out.println("6. Filter Payments by Payment Method");
            System.out.println("7. Exit");
            System.out.println("8. Enter the choice:");
            int choice = scanner.nextInt();

            switch (choice) {
            case 1:
                System.out.print("Enter PID: ");
                String PID = scanner.next();
                if (PID.isEmpty()) {
                    System.out.println("PID cannot be empty.");
                    continue;
                }

                System.out.print("Enter Vehicle Type: ");
                String vehicleType = scanner.next();
                if (vehicleType.isEmpty()) {
                    System.out.println("Vehicle Type cannot be empty.");
                    continue;
                }

                System.out.print("Enter Payment Method: ");
                String paymentMethod = scanner.next();
                if (paymentMethod.isEmpty()) {
                    System.out.println("Payment Method cannot be empty.");
                    continue;
                }

                System.out.print("Enter Driver Amount: ");
                double driverAmount;
                try {
                    driverAmount = scanner.nextDouble();
                    if (driverAmount < 0) {
                        System.out.println("Driver Amount cannot be negative.");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Driver Amount. Please enter a valid number.");
                    scanner.next(); 
                    continue;
                }

                System.out.print("Enter Vehicle Amount: ");
                double vehicleAmount;
                try {
                    vehicleAmount = scanner.nextDouble();
                    if (vehicleAmount < 0) {
                        System.out.println("Vehicle Amount cannot be negative.");
                        continue;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Vehicle Amount. Please enter a valid number.");
                    scanner.next(); 
                    continue;
                }

                Payment newPayment = new Payment(PID, vehicleType, paymentMethod, driverAmount, vehicleAmount);
                paymentService.addPayment(newPayment);
                break;
                
            case 2:
                System.out.print("Enter PID to Update: ");
                String updatePid = scanner.next();
                System.out.print("Enter New Vehicle Type: ");
                String newVehicleType = scanner.next();
                System.out.print("Enter New Payment Method: ");
                String newPaymentMethod = scanner.next();
                
                if (paymentService.updatePayment(updatePid, newVehicleType, newPaymentMethod)) {
                    System.out.println("Payment updated successfully.");
                } else {
                    System.out.println("Payment with PID " + updatePid + " not found.");
                }
                break;

            case 3:
                List<Payment> payments = paymentService.displayPayments();
                if (payments.isEmpty()) {
                    System.out.println("No payments found.");
                } else {
                    System.out.println("\n--- Payment Details Table ---");
                    System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", 
                        "PID", "Vehicle Type", "Payment Method", "Driver Amount", "Vehicle Amount", "Total Amount");
                    System.out.println("-----------------------------------------------");

                    for (Payment payment : payments) {
                        double totalAmount = payment.getTotalAmount();
                        System.out.printf("%-10s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", 
                            payment.getPid(), payment.getVehicleType(), payment.getPaymentMethod(), 
                            payment.getDriverAmount(), payment.getVehicleAmount(), totalAmount);
                    }

                    System.out.println("-----------------------------------------------");
                }
                break;


            case 4:
                System.out.print("Enter Vehicle Type to Calculate Total: ");
                String calcVehicleType = scanner.next();
                double total = paymentService.calculateTotalByVehicleType(calcVehicleType);
                System.out.println("Total Amount for Vehicle Type " + calcVehicleType + ": " + total);
                break;

            case 5:
                System.out.print("Enter PID to Delete: ");
                String deletePid = scanner.next();
                paymentService.deletePayment(deletePid);
                System.out.println("Payment deleted successfully.");
                break;

            case 6:
                System.out.print("Enter Payment Method to Filter (e.g., Visa, Cash): ");
                String filterMethod = scanner.next();
                List<Payment> filteredPayments = paymentService.filterPaymentsByMethod(filterMethod);
                if (filteredPayments.isEmpty()) {
                    System.out.println("No payments found for method " + filterMethod);
                } else {
                    System.out.println("\n--- Filtered Payments ---");
                    System.out.printf("%-10s %-15s %-15s %-10s %-10s %-10s\n", 
                        "PID", "Vehicle Type", "Payment Method", "Driver Amount", "Vehicle Amount", "Total Amount");
                    System.out.println("-----------------------------------------------");

                    for (Payment payment : filteredPayments) {
                        double totalAmount = payment.getTotalAmount();
                        System.out.printf("%-10s %-15s %-15s %-10.2f %-10.2f %-10.2f\n", 
                            payment.getPid(), payment.getVehicleType(), payment.getPaymentMethod(), 
                            payment.getDriverAmount(), payment.getVehicleAmount(), totalAmount);
                    }

                    System.out.println("-----------------------------------------------");
                }
                break;



          }
            
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(serviceReference);
        System.out.println("Consumer Stopped.");
    }
}
