package com.pearson.statsagg.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeffrey Schmidt
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class.getName());

    public static Integer getIntegerFieldFromJsonObject(JsonObject jsonObject, String fieldName) {
        
        if (jsonObject == null) {
            return null;
        }
        
        Integer returnInteger = null;
        
        try {
            JsonElement jsonElement = jsonObject.get(fieldName);
            if (jsonElement != null) returnInteger = jsonElement.getAsInt();
        }
        catch (Exception e) {
            logger.error(e.toString() + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));    
        }

        return returnInteger;
    }
    
    public static String getStringFieldFromJsonObject(JsonObject jsonObject, String fieldName) {
        
        if (jsonObject == null) {
            return null;
        }
        
        String returnString = null;
        
        try {
            JsonElement jsonElement = jsonObject.get(fieldName);
            
            if (jsonElement != null) {
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                returnString = jsonPrimitive.getAsString();
            }
        }
        catch (Exception e) {
            logger.error(e.toString() + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));    
        }

        return returnString;
    }
    
    public static Boolean getBooleanFieldFromJsonObject(JsonObject jsonObject, String fieldName) {
        
        if (jsonObject == null) {
            return null;
        }
        
        Boolean returnBoolean = null;
        
        try {
            JsonElement jsonElement = jsonObject.get(fieldName);
            if (jsonElement != null) returnBoolean = jsonElement.getAsBoolean();
        }
        catch (Exception e) {
            logger.error(e.toString() + System.lineSeparator() + StackTrace.getStringFromStackTrace(e));    
        }

        return returnBoolean;
    }
    
}
