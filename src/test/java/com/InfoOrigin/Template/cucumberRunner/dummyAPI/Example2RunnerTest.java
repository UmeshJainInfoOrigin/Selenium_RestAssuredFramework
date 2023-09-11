package com.InfoOrigin.Template.cucumberRunner.dummyAPI;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/com/swissre/ioms/features",
        glue = "com/InfoOrigin/Template/stepDefinitions",
        plugin = { "pretty", "html:target/cucumber-reports/CucumberRunnerTest-reports.html",
                "json:target/cucumber-reports/CucumberRunnerTest.json"},
        monochrome = true)
public class Example2RunnerTest extends AbstractTestNGCucumberTests {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Example2RunnerTest.class);
    @BeforeSuite()
    public static void beforeAll() {
        LOG.info("This is BeforeSuite... for Example2 ");
    }
    @BeforeClass()
    public static void beforeClass() {
        LOG.info("This is BeforeSuite... for Example2 ");
    }

    @AfterClass
    public static void afterClass() {
        LOG.info("This is BeforeSuite... for Example2 ");
    }
    @AfterSuite
    public static void afterAll() {
        LOG.info("This is BeforeSuite... for Example2 ");
    }
}
