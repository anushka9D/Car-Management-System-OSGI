package olivea_payment_producer;

public class Payment {
    private String PID;
    private String vehicleType;
    private String paymentMethod;
    private double driverAmount;
    private double vehicleAmount;

    public Payment(String PID, String vehicleType, String paymentMethod, double driverAmount, double vehicleAmount) {
        this.PID = PID;
        this.vehicleType = vehicleType;
        this.paymentMethod = paymentMethod;
        this.driverAmount = driverAmount;
        this.vehicleAmount = vehicleAmount;
    }

    // Getters and Setters
    public String getPid() {
        return PID;
    }

    public void setPid(String PID) {
        this.PID = PID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getDriverAmount() {
        return driverAmount;
    }

    public void setDriverAmount(double driverAmount) {
        this.driverAmount = driverAmount;
    }

    public double getVehicleAmount() {
        return vehicleAmount;
    }

    public void setVehicleAmount(double vehicleAmount) {
        this.vehicleAmount = vehicleAmount;
    }

    public double getTotalAmount() {
        return driverAmount + vehicleAmount;
    }
}
