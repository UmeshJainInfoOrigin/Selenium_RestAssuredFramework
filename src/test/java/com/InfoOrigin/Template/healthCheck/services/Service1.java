package com.InfoOrigin.Template.healthCheck.services;


import testAutomation.common.healthCheck.services.HealthCheckService;

public class Service1 implements HealthCheckService {
    // code for verifying whether the service is up or not
    @Override
	public Boolean isServiceUp() {
		return true;
	}
}
