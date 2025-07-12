package ashandth_drivermanagement_producer;

public interface DriverService {
    String registerDriver(String name, String licenseNumber, String phoneNumber, String nic, String vehicleType);
    String viewAllDrivers();
    String searchDriverById(String did);
    String updateDriverDetails(String did, String name, String licenseNumber, String phoneNumber, String nic, String vehicleType);
    String deleteDriver(String did);
    String checkVehicleDriven(String did);
    String checkCustomerTraveled(String did);
    String checkSalaryWithPaymentId(String did, String pid);
   
}