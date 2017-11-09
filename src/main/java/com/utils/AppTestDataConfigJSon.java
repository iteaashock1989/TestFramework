package com.utils;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AppTestDataConfigJSon {
	
	public static String readData(String data) {
		
		String name = null;
		JSONParser parser = new JSONParser();        
        try
        {
            Object obj = parser.parse(new FileReader("src/test/resources/testData/appData.json"));		       	 
            JSONObject jsonObject = (JSONObject) obj;        	
        	name = (String) jsonObject.get(data);
        	//System.out.println("Name: " + name);
        } catch (Exception e) {
	        e.printStackTrace();
	    }
		return name;
    }
}