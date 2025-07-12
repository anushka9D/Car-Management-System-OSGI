package car_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CarProducerActivator implements BundleActivator {

    private ServiceRegistration serviceRegistration;

    public void start(BundleContext context) throws Exception {
        System.out.println("Car Management Service Started...");
        CarService carService = new CarServiceImpl();
        serviceRegistration = context.registerService(CarService.class.getName(), carService, null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Car Management Service Stopped...");
        serviceRegistration.unregister();
    }
}
