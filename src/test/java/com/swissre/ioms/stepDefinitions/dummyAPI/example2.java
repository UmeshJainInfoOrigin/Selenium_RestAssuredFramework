package com.swissre.ioms.stepDefinitions.dummyAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swissre.ioms.constants.Constants;
import com.swissre.ioms.crud.CommonUtilForIOOperation;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import testAutomation.api.apiAssertsCheck.APIAsserts;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import testAutomation.api.restAssuredAPI.RestCallWrappers;

import java.io.IOException;

public class example2 {

    CommonUtilForIOOperation commonUtils = new CommonUtilForIOOperation();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(example2.class.getName());
     Response response;

    @When("User enters the text")
    public void user_enters_the_text() {
        APIAsserts apiAsserts= new APIAsserts();
        Assert.assertEquals(apiAsserts.isOk(),apiAsserts.isOk());
        LOG.info("This is when part for second example");
    }
    @Then("User should see the text")
    public void user_should_see_the_text() throws InterruptedException {
        APIAsserts apiAsserts= new APIAsserts();
        Assert.assertEquals(apiAsserts.isOk(),apiAsserts.isOk());
        LOG.info("This is when part for second example");
    }

    @When("User enter {string} to open google")
    public void userEnterToOpenGoogle(String arg0) throws JsonProcessingException {
        RestCallWrappers request = new RestCallWrappers("iosetsService");
        String endPoint = Constants.GOOGLE_URL;
        request.doGet(endPoint);
        this.response = request.getResponse();
    }

    @Then("Ensure that user will get isOk response")
    public void ensureThatUserWillGetIsOkResponse() throws InterruptedException {
        Assert.assertEquals(this.response.getStatusCode(), new APIAsserts().isOk());
    }

    @When("Compare {string} is equals to {string}")
    public void compareIsEqualsTo(String filename, String jsonFileName) throws IOException, ParseException {
        RestCallWrappers request = new RestCallWrappers("iosetsService");
        JSONObject exp = commonUtils.getJSONData(filename);
        JSONObject act = commonUtils.getJSONData(jsonFileName);
        String expjson = exp.toString();
        String actjson = act.toString();
        JSONAssert.assertEquals(actjson, expjson, JSONCompareMode.STRICT);
    }
}
