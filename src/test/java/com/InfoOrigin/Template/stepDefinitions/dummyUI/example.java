package com.InfoOrigin.Template.stepDefinitions.dummyUI;

import com.InfoOrigin.Template.stepDefinitions.sharedHooks.scenarioHook;
import com.InfoOrigin.Template.stepDefinitions.utils.dummyUIStepUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import testAutomation.common.ReadProperties;
import testAutomation.ui.SeleniumLibWrapper;
import testAutomation.utils.GetScreenShot;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class example {
    private ReadProperties properties = ReadProperties.getInstance();
    dummyUIStepUtils utils = new dummyUIStepUtils();
    scenarioHook scenarioContext;
    scenarioDataCache dataCache = new scenarioDataCache().getInstance();
    GetScreenShot attach = new GetScreenShot();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(example.class);

    public example(scenarioHook scenario_context) throws IOException, ParseException {
        System.out.println("...Scenario Name..."+scenario_context);
        this.scenarioContext = scenario_context;
        this.setJson(this.scenarioContext.getScenarioName());
    }

//    private example(scenarioHook scenarioContext) throws IOException, ParseException {
//        this.scenarioContext = scenarioContext;
//        System.out.println("...Scenario Name..."+scenarioContext);
//        this.setJson(this.scenarioContext.getScenarioName());
//    }
    void setJson (String filName) throws IOException, ParseException {
        System.out.println("...filName..."+filName);
        dataCache.put(filName);
    }
    SeleniumLibWrapper LB = new SeleniumLibWrapper();

    @Then("User verify the Home button")
    public void userVerifyTheHomeButton() {
        System.out.println("..azzzzzzzzzzzzzz....");
        Assert.assertEquals(LB.getText("(//a[text()='Home'])[1]"), "Home");
    }

    @And("Verify the text {string} as {string}")
    public void verifyTheTextHomeTextAsHome(String path, String text) throws IOException, ParseException {
        Assert.assertEquals(LB.getText(utils.getXpath(path, dataCache.get(this.scenarioContext.getScenarioName()))), text);
    }

    @Then("Verify the {string} as {string}")
    public void verifyTheAs(String path, String text) throws IOException, ParseException {
        Assert.assertEquals(LB.verify_text(utils.getXpath(path, dataCache.get(this.scenarioContext.getScenarioName()))), text);
    }

    @When("User clicks on {string}")
    public void user_clicks_on(String path) throws IOException, ParseException {
        // Get the start time
        long startTime = System.nanoTime();
        //Scenario:locators: Scenario Verify Home Contact Us Menu in InfoOrigin website
        String getScenarioName = this.scenarioContext.getScenarioName(); //locators
        //"ContactUs-Menu": "//a[contains(text(),'Contact Us')]"
        JSONObject getLocators = dataCache.get(getScenarioName); //it will open json and keep all data in memory
        //Click function
        String objectValue = utils.getXpath(path, getLocators); // //a[contains(text(),'Contact Us')]
        LB.click(objectValue);
        // Get the end time
        long endTime = System.nanoTime();
        // Calculate and print the execution time in milliseconds
        long executionTime = (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds
        System.out.println("Execution time for the automated test case: " + executionTime + " ms");
    }

    @And("User fills the message form")
    public void userFillsTheMessageForm(DataTable table) throws IOException, ParseException {
        List<Map<String, String>> listdata = table.asMaps(String.class, String.class);
        Stack<String> arr =  new Stack<>();
        for (String keys: listdata.get(0).keySet()){
            arr.push(keys);
        }
        LB.enterText(utils.getXpath(arr.get(0), dataCache.get(this.scenarioContext.getScenarioName())), listdata.get(0).get(arr.get(0)));
        LB.enterText(utils.getXpath(arr.get(1), dataCache.get(this.scenarioContext.getScenarioName())), listdata.get(0).get(arr.get(1)));
        LB.enterText(utils.getXpath(arr.get(2), dataCache.get(this.scenarioContext.getScenarioName())), listdata.get(0).get(arr.get(2)));
        LB.enterText(utils.getXpath(arr.get(3), dataCache.get(this.scenarioContext.getScenarioName())), listdata.get(0).get(arr.get(3)));
    }

    @Then("User should verify {string}")
    public void userShouldVerify(String path) throws IOException, ParseException {
        LB.verify_text(utils.getXpath(path, dataCache.get(this.scenarioContext.getScenarioName())));
    }

    @When("User enters {string} text as {string}")
    public void userEntersTextAs(String path, String text) throws IOException, ParseException {
        LB.enterText(utils.getXpath(path, dataCache.get(this.scenarioContext.getScenarioName())), text);
    }

    @When("Enter {string} text as {string}")
    public void enter_text_as(String path, String text) throws IOException, ParseException {
        LB.enterText(utils.getXpath(path, dataCache.get(this.scenarioContext.getScenarioName())), text);
    }

}

