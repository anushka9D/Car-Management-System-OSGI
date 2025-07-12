package ashandth_drivermanagement_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {
    private ServiceRegistration<DriverService> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        // Create an instance of the DriverServiceImpl
        DriverService driverService = new DriverServiceImpl();

        // Register the DriverService as an OSGi service
        registration = context.registerService(DriverService.class, driverService, null);

        System.out.println("Driver Producer Bundle Started. DriverService registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the DriverService when the bundle stops
        if (registration != null) {
            registration.unregister();
            System.out.println("Driver Producer Bundle Stopped. DriverService unregistered.");
        }
    }
}