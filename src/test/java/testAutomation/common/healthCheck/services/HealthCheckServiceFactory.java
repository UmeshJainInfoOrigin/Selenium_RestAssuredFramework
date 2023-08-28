package testAutomation.common.healthCheck.services;

import testAutomation.common.ReadProperties;

public class HealthCheckServiceFactory {
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static HealthCheckService getService(String key) {
    	try {
			String className = ReadProperties.getInstance().getValue(key+".healthcheck.class");
			Class serviceClazz = Class.forName(className);
			HealthCheckService service = (HealthCheckService) serviceClazz.getDeclaredConstructor().newInstance();
			return service;
		} catch (Exception ex) {
			throw new RuntimeException("Exception while creating Service instance for "+key, ex );
		}
    	
    }
}
