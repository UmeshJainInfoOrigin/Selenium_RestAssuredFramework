package com.swissre.Sample_TestProject.UIRunner;


import com.swissre.ioms.testSuites.dummyUI.CucumberRunnerTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import testAutomation.common.healthCheck.HealthCheck;
import testAutomation.common.userAuthCheck.Auth;
import testAutomation.common.userAuthCheck.AuthProvider;

@CucumberOptions(tags = "@SampleAPITest",
        features = "src/test/java/com/swissre/Sample_TestProject/sample_features", glue = "com/swissre/Sample_TestProject/sample_StepDefinitions",
        plugin = { "pretty", "html:target/CucumberRunnerTest-SampleReport.html" },
        monochrome = true)

public class UIRun extends AbstractTestNGCucumberTests {
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
        LOG.info("This is CucumberRunnerSampleTest AfterSuite");
    }

    @BeforeClass()
    public void beforeClass() {
        LOG.info("This is CucumberRunnerSampleTest BeforeClass ");
    }

    @AfterClass
    public void afterClass() {
        LOG.info("This is CucumberRunnerSampleTest AfterClass");
    }

}
