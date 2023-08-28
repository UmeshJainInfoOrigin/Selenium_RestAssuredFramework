package com.swissre.ioms.stepDefinitions.dummyUI;

import com.swissre.ioms.stepDefinitions.utils.dummyUIStepUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.HashMap;

public class scenarioDataCache {

    private static scenarioDataCache instance;
    private HashMap cache;

    scenarioDataCache() {
        this.cache = new HashMap<>();
    }

    public static synchronized scenarioDataCache getInstance() {
        if (instance == null) {
            instance = new scenarioDataCache();
        }
        return instance;
    }

    public boolean isKeyPresent(String key) {
        return this.cache.containsKey(key) && this.cache.get(key) != null;
    }

    public JSONObject get(String key) throws IOException, ParseException {
        System.out.println("..get..."+key);
        if(!this.isKeyPresent(key)){
            dummyUIStepUtils utils = new dummyUIStepUtils();
            this.cache.put(key, utils.getXpathJsonObject(key));
        }
        return (JSONObject) this.cache.get(key);
    }

    public void put(String key) throws IOException, ParseException {
        if(!this.isKeyPresent(key)) {
            dummyUIStepUtils utils = new dummyUIStepUtils();
            this.cache.put(key, utils.getXpathJsonObject(key));
        }
    }




}
