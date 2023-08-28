package testAutomation.api.restAssuredAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testAutomation.common.ReadProperties;


import java.util.Map;

public class RestCallWrappers {
    private RequestSpecification request;
    private Response response;

    public RestCallWrappers(String serviceKey){
        RestAssured.baseURI = ReadProperties.getInstance().getValue(serviceKey+".url");
        this.request = RestAssured.given();
    }

    public void setHeaders(Map<String, ?> headerObject){
        if(headerObject!=null){
            this.request.headers(headerObject);
        }
    }

    public void setPayload(Map data) throws JsonProcessingException {
        if(data!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            String mapToJson = objectMapper.writeValueAsString(data);
            this.request.body(mapToJson);
        }
    }
    public void doGet(String endpoint) {
         this.response = this.request.when().get(endpoint).andReturn();
    }

    public void doPost(String endpoint) {
        this.response = this.request.when().post(endpoint).andReturn();
    }

    public void doPut(String endpoint) {
        this.response = this.request.when().put(endpoint).andReturn();
    }

    public void doDelete(String endpoint) {
        this.response = this.request.when().delete(endpoint).andReturn();
    }

    public Response getResponse() {
        return this.response;
    }
}
