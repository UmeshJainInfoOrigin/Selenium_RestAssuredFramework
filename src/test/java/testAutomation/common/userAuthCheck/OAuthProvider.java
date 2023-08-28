package testAutomation.common.userAuthCheck;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.Header;
import org.json.JSONObject;
import testAutomation.common.ReadProperties;

public class OAuthProvider implements Auth{
    public String getAccessToken() {
//        Response response;
//        response =
//                (Response) RestAssured.given()
//                        .auth()
//                        .preemptive()
//                        .basic(ReadProperties.getInstance().getValue("ClientId"), ReadProperties.getInstance().getValue("ClientSecret"))
//                        .contentType("application/x-www-form-urlencoded")
//                        .formParam("grant_type", "client_credentials")
//                        .formParam("scope", ReadProperties.getInstance().getValue("Scope"))
//                        .when()
//                        .post(ReadProperties.getInstance().getValue("TokenURL"));
//        JSONObject jsonObject = new JSONObject(response.getBody().asString());
//        return jsonObject.get("access_token").toString();

        return null;
    }

    public Boolean authenticate() {
        return false;
    }

    public Header getHeader() {
        return null;
    }

    public Object getPayload() {
        return null;
    }

    public String getClientId() {
        return null;
    }

    public Object getUserDetails() {
        return null;
    }
}
