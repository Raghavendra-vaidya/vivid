package com.vivid.ProjectUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utilities {

    public static JsonObject getJsonFileObject(String filePath){
        JsonObject fileObj = null;
        Path path =Paths.get(filePath);
        try
        {
            Reader reader = Files.newBufferedReader(path);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            fileObj = jsonElement.getAsJsonObject();
        }
        catch (Exception e){e.printStackTrace();}
        return fileObj;
    }

}
