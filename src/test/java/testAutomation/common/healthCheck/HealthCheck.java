package testAutomation.common.healthCheck;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import testAutomation.common.healthCheck.services.ServicesHealthCheck;


public class HealthCheck {
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(HealthCheck.class);
    
    public boolean getHealthCheck()  {
        // Health check for services
    	ArrayList<String> downServices = new ServicesHealthCheck().doServiceHealthCheck();
    	if(!downServices.isEmpty()) {
    		LOG.error("These services are down :"+downServices);
    	}
        return downServices.isEmpty();
    }
}
