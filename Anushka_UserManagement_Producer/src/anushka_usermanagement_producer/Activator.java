package anushka_usermanagement_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration publishServiceRegistration;
    
    public void start(BundleContext context) throws Exception {
    	
        System.out.println("User Details Management Producer Started");
        UserManagement userService = new UserManagementImpl();
        
        publishServiceRegistration = context.registerService(UserManagement.class.getName(), userService, null);
        
    }
    
    public void stop(BundleContext context) throws Exception {
    	
        System.out.println("User Details Management Producer Stopped");
        
        publishServiceRegistration.unregister();
    }

}
