package testAutomation.common.healthCheck.services;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ServicesHealthCheck {
    public ArrayList<String> doServiceHealthCheck() {
        try {
			// Number of services to check
			String envName=System.getProperty("EnvName");
			if(envName==null){
				envName="template_dev";
			}
			JSONParser jsonP = new JSONParser();
			FileReader reader = new FileReader("src/test/resources/healthConfiguration.json");
			JSONObject obj = (JSONObject)jsonP.parse(reader);
			ArrayList<String> downServices = new ArrayList<>();
			checkServices((JSONObject) obj.get(envName), downServices);
			return downServices;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void checkServices(JSONObject obj, ArrayList<String> downServices) {
        obj.entrySet().stream().forEach(service->{
            Map.Entry serviceMap = (Map.Entry) service;
            String serviceKey = (String) serviceMap.getKey();
            if(!isServiceUp(serviceKey)) {
            	downServices.add(serviceKey);
            	return;
            }

            if(!ObjectUtils.isEmpty(serviceMap.getValue())){
                checkServices((JSONObject) serviceMap.getValue(),downServices);
            }
        });
    }

    private boolean isServiceUp(String key) {
    	HealthCheckService service = HealthCheckServiceFactory.getService(key);
    	return service.isServiceUp();
    }
}
