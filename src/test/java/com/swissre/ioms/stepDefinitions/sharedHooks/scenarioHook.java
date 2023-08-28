package com.swissre.ioms.stepDefinitions.sharedHooks;

import io.cucumber.java.Scenario;
import io.cucumber.java.Before;

public class scenarioHook {

    private String scenarioName;

    public String getScenarioName() {
        System.out.println("...scenarioName...."+scenarioName);
        return scenarioName;
    }

    public void setScenarioName (String scenarioName) {
        System.out.println("...set scenarioName...."+scenarioName);
        this.scenarioName = scenarioName;
    }


    @Before
    public void before_Scenario(Scenario scenario) {
        System.out.println("...before scenarioName...."+scenario);
        this.setScenarioName(scenario.getName().split(":")[0]);
    }


}
