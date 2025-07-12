package ashandth_drivermanagement_producer;

public class DriverServiceImpl implements DriverService {
    private Driver driver = new DriverImpl();

    @Override
    public String registerDriver(String name, String licenseNumber, String phoneNumber, String nic, String vehicleType) {
        return driver.registerDriver(name, licenseNumber, phoneNumber, nic, vehicleType);
    }

    @Override
    public String viewAllDrivers() {
        return driver.viewAllDrivers();
    }

    @Override
    public String searchDriverById(String did) {
        return driver.searchDriverById(did);
    }

    @Override
    public String updateDriverDetails(String did, String name, String licenseNumber, String phoneNumber, String nic, String vehicleType) {
        return driver.updateDriverDetails(did, name, licenseNumber, phoneNumber, nic, vehicleType);
    }

    @Override
    public String deleteDriver(String did) {
        return driver.deleteDriver(did);
    }

    @Override
    public String checkVehicleDriven(String did) {
        return driver.checkVehicleDriven(did);
    }

    @Override
    public String checkCustomerTraveled(String did) {
        return driver.checkCustomerTraveled(did);
    }

    @Override
    public String checkSalaryWithPaymentId(String did, String pid) {
        return driver.checkSalaryWithPaymentId(did, pid);
    }

    
}