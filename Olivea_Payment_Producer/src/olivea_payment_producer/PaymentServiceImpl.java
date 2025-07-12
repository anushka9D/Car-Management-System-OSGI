package olivea_payment_producer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {

    private List<Payment> payments = new ArrayList<>();

    
    public void addPayment(Payment payment) {
        payments.add(payment);
        System.out.println("Payment added successfully!");
    }

    
    public boolean updatePayment(String pid, String newVehicleType, String newPaymentMethod) {
        for (Payment payment : payments) {
            if (payment.getPid().equals(pid)) {
                payment.setVehicleType(newVehicleType);
                payment.setPaymentMethod(newPaymentMethod);
                System.out.println("Payment updated successfully!");
                return true;
            }
        }
        System.out.println("Payment with PID " + pid + " not found.");
        return false;
    }

    
    public List<Payment> displayPayments() {
        return payments;
    }

    
    public double calculateTotalByVehicleType(String vehicleType) {
        double total = 0.0;
        for (Payment payment : payments) {
            if (payment.getVehicleType().equalsIgnoreCase(vehicleType)) {
                total += payment.getTotalAmount();
            }
        }
        return total;
    }
    
    public void deletePayment(String pid) {
        payments.removeIf(payment -> payment.getPid().equals(pid));
    }

    
    public List<Payment> filterPaymentsByMethod(String paymentMethod) {
        return payments.stream()
            .filter(payment -> payment.getPaymentMethod().equalsIgnoreCase(paymentMethod))
            .collect(Collectors.toList());
    }



}
