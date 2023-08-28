package com.swissre.ioms.testSuites.dummyUI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import testAutomation.common.ReadProperties;
import testAutomation.common.healthCheck.HealthCheck;
import testAutomation.common.userAuthCheck.Auth;
import testAutomation.common.userAuthCheck.AuthProvider;
import testAutomation.ui.SeleniumLibWrapper;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/java/com/swissre/ioms/features", glue = "com/swissre/ioms/stepDefinitions",
        plugin = { "pretty", "html:target/cucumber-reports/CucumberRunnerTest-reports.html",
                "json:target/cucumber-reports/CucumberRunnerTest.json"}
        )
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
    private SeleniumLibWrapper LB = new SeleniumLibWrapper().getInstance();
    private ReadProperties properties = new ReadProperties().getInstance();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CucumberRunnerTest.class);
    @BeforeSuite()
    public void beforeAll(){
        HealthCheck healthCheck= new HealthCheck();
        if(!healthCheck.getHealthCheck()) {
        	LOG.error("Healthcheck failed");
        	throw new RuntimeException("Healthcheck failed");
        }

        AuthProvider authProvider= new AuthProvider();
        Auth provider=authProvider.getAuthProvider();
        provider.authenticate();
        LOG.info("This is CucumberRunnerTest BeforeSuite");
    }

    @AfterSuite
    public void afterAll() {
        LOG.info("This is CucumberRunnerTest AfterSuite");
    }

    @BeforeClass()
    public void launchBrowser() {
        LB.browserLaunch(properties.getValue("browserType"), "InfoOrigin");
    }

    @AfterClass
    public void destroyBrowser() {
        LB.killBrowser();
    }
}