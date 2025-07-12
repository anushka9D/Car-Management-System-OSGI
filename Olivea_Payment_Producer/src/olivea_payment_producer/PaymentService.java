package olivea_payment_producer;


import java.util.List;

public interface PaymentService {
    void addPayment(Payment payment);
    boolean updatePayment(String pid, String newVehicleType, String newPaymentMethod);
    List<Payment> displayPayments();
    double calculateTotalByVehicleType(String vehicleType);
    void deletePayment(String pid);
    List<Payment> filterPaymentsByMethod(String paymentMethod);
}
