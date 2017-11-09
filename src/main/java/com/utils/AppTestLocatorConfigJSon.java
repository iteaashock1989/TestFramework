package com.utils;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

public class AppTestLocatorConfigJSon {
	
	public static By readLoc(String data) {
		
		String name;String locType, locValue;
    	String[] byLoc;String[] byLoc1;
    	By locPath = null;
    	
		JSONParser parser = new JSONParser();
        
        try
        {
            Object obj = parser.parse(new FileReader("src/test/resources/testData/appLocators.json"));		       	 
            JSONObject jsonObject = (JSONObject) obj;
        	
        	name = (String) jsonObject.get(data);
        	//System.out.println("Name: " + name);	
        	
        	byLoc = name.split("\\('");   
        	locType = byLoc[0];
        	
        	byLoc1 = byLoc[1].split("'\\)"); 
        	locValue = byLoc1[0];
        	
        	locPath = locator(locType,locValue);

        } catch (Exception e) {
	        e.printStackTrace();
	    }
		return locPath;
    }
	
	public static By locator(String locatorType, String locatorValue) {
		
		By locatorPath = null;
		
		if(locatorType.contains("By.xpath"))
		{        		
			locatorPath = By.xpath(locatorValue);
			
		} else if(locatorType.contains("By.id"))
		{        		
			locatorPath = By.id(locatorValue);
			
		} else if(locatorType.contains("By.name"))
		{        		
			locatorPath = By.name(locatorValue);
			
		} else if(locatorType.contains("By.linkText"))
		{
			locatorPath = By.linkText(locatorValue);
			
		} else if(locatorType.contains("By.partialLinkText"))
		{
			locatorPath = By.partialLinkText(locatorValue);
			
		} else if(locatorType.contains("By.tagName"))
		{
			locatorPath = By.tagName(locatorValue); 
			
		} else if(locatorType.contains("By.className"))
		{
			locatorPath = By.className(locatorValue);
			
		} else if(locatorType.contains("By.cssSelector"))
		{
			locatorPath = By.cssSelector(locatorValue);
		} 		
		return locatorPath;		
	}
}