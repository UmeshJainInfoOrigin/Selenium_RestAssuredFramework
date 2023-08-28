package com.swissre.ioms.stepDefinitions.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import testAutomation.common.ReadProperties;

import java.io.FileReader;
import java.io.IOException;

public class dummyUIStepUtils {

    public JSONObject getXpathJsonObject(String filename) throws IOException, ParseException {
        JSONParser jsonP = new JSONParser();
        System.out.println("....jsonFilePath...."+"src/test/java/com/swissre/ioms/jsonData/"+ filename + ".json");
        FileReader reader = new FileReader("src/test/java/com/swissre/ioms/jsonData/"+ filename + ".json");
        return (JSONObject) jsonP.parse(reader);
    }

    public String alterKey(String key) {
        System.out.println("...Key in alterkey...."+key);
        return  key.trim().replaceAll(" ", "-");
    }

    public String getXpath(String key, JSONObject jsonData) {
        System.out.println("...Key...."+key);
        return (String) jsonData.get(this.alterKey(key));
    }

}
