package ashandth_drivermanagement_producer;

import java.util.HashMap;
import java.util.Map;

public class DriverImpl implements Driver {
    private Map<String, DriverDetails> drivers = new HashMap<>();

    @Override
    public String registerDriver(String name, String licenseNumber, String phoneNumber, String nic, String vehicleType) {
        String did = generateDriverId();
        drivers.put(did, new DriverDetails(name, licenseNumber, phoneNumber, nic, vehicleType));
        return "Driver registered with ID: " + did;
    }

    @Override
    public String viewAllDrivers() {
        StringBuilder table = new StringBuilder();
        // Table header
        table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
        table.append("| Driver ID  | Name            | License Number  | Phone Number    | NIC             | Vehicle Type    |\n");
        table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

        // Table rows
        for (Map.Entry<String, DriverDetails> entry : drivers.entrySet()) {
            String did = entry.getKey();
            DriverDetails driver = entry.getValue();
            table.append(String.format(
                "| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                did, driver.getName(), driver.getLicenseNumber(), driver.getPhoneNumber(), driver.getNic(), driver.getVehicleType()
            ));
        }

        // Table footer
        table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
        return table.toString();
    }

    @Override
    public String searchDriverById(String did) {
        DriverDetails driver = drivers.get(did);
        return driver != null ? driver.toString() : "Driver not found";
    }

    @Override
    public String updateDriverDetails(String did, String name, String licenseNumber, String phoneNumber, String nic, String vehicleType) {
        if (drivers.containsKey(did)) {
            drivers.put(did, new DriverDetails(name, licenseNumber, phoneNumber, nic, vehicleType));
            return "Driver details updated";
        }
        return "Driver not found";
    }

    @Override
    public String deleteDriver(String did) {
        if (drivers.containsKey(did)) {
            drivers.remove(did);
            return "Driver deleted";
        }
        return "Driver not found";
    }

    @Override
    public String checkVehicleDriven(String did) {
        DriverDetails driver = drivers.get(did);
        return driver != null ? "Vehicle driven: " + driver.getVehicleType() : "Driver not found";
    }

    @Override
    public String checkCustomerTraveled(String did) {
        // Placeholder for actual implementation
        return "Customer traveled with driver " + did;
    }

    @Override
    public String checkSalaryWithPaymentId(String did, String pid) {
        // Placeholder for actual implementation
        return "Salary for driver " + did + " with payment ID " + pid;
    }

    private String generateDriverId() {
        return String.format("%05d", drivers.size() + 1);
    }

    private class DriverDetails {
        private String name;
        private String licenseNumber;
        private String phoneNumber;
        private String nic;
        private String vehicleType;

        public DriverDetails(String name, String licenseNumber, String phoneNumber, String nic, String vehicleType) {
            this.name = name;
            this.licenseNumber = licenseNumber;
            this.phoneNumber = phoneNumber;
            this.nic = nic;
            this.vehicleType = vehicleType;
        }

        public String getName() {
            return name;
        }

        public String getLicenseNumber() {
            return licenseNumber;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getNic() {
            return nic;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        @Override
        public String toString() {
            // Table format for single driver details
            StringBuilder table = new StringBuilder();
            table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
            table.append("| Driver ID  | Name            | License Number  | Phone Number    | NIC             | Vehicle Type    |\n");
            table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
            table.append(String.format(
                "| %-10s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                "N/A", name, licenseNumber, phoneNumber, nic, vehicleType
            ));
            table.append("+------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
            return table.toString();
        }
    }
}