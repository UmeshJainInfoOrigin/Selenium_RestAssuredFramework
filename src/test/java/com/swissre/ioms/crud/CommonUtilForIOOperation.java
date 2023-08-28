package com.swissre.ioms.crud;

import com.swissre.ioms.constants.Constants;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class CommonUtilForIOOperation {
    public JSONObject getJSONData(String fileName) throws IOException, ParseException {
        JSONParser jsonP = new JSONParser();
        FileReader reader = new FileReader("src/test/java/com/swissre/ioms/jsonData/" + fileName);
        return (JSONObject) jsonP.parse(reader);
    }

    public JSONObject convertResponseBodyToJSONObject(ResponseBody body) throws ParseException {
        JSONParser jsonP = new JSONParser();
        JSONObject responseAsObject = (JSONObject) jsonP.parse(body.asString());
        return responseAsObject;
    }

}
