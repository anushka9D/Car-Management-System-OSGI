package olivea_payment_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaymentProducerActivator implements BundleActivator {

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        // Register the PaymentService implementation
        registration = context.registerService(
            PaymentService.class, // Use class reference directly
            new PaymentServiceImpl(), 
            null
        );
        System.out.println("Payment Producer Service Started.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (registration != null) {
            registration.unregister(); // Unregister service
        }
        System.out.println("Payment Producer Service Stopped.");
    }
}
