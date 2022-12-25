package com.vivid.configuration;

import com.google.gson.JsonObject;
import com.vivid.ProjectUtils.Utilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class LoadConfig {

    public DesiredCapabilities loadDeviceCaps(String fileName){
        JsonObject capabilities = Utilities.getJsonFileObject(fileName);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Map<String, String> commonCapabilities = (Map<String, String>) capabilities.get("capabilities");




        return desiredCapabilities;
    }
}
