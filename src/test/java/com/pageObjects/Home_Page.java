package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.AppTestLocatorConfigJSon;
import com.utils.Log;
import com.utils.Utils;

    public class Home_Page extends Utils {

		public Home_Page(WebDriver getDriver) {
        	this.getDriver = getDriver;
		}

        public void searchItem(String data, String loc) throws Exception {
        	
        	try{
            	waitForElementToBePresent(loc);
            	typeText(data, loc);
	            Log.info("Log Out link is found on the Home Page");
            }catch (Exception e){
            	Log.error("Log Out link is not found on the Home Page");
           		throw(e);
           		}      	
        }
        
        public void loginApp(String username, String password) throws Exception {
        	
        	By usernameLoc = AppTestLocatorConfigJSon.readLoc("Username");
        	By passwordLoc = AppTestLocatorConfigJSon.readLoc("Password");
        	
        	try{
            	waitForElementToBePresent("Username");
            	getDriver.findElement(usernameLoc).sendKeys(username);
	            Log.info("Log Out link is found on the Home Page");
	            
	            waitForElementToBePresent("Password");
            	getDriver.findElement(passwordLoc).sendKeys(password);
	            Log.info("Log Out link is found on the Home Page");
	            
	            waitForElementToBePresent("LoginInButton");
            	clickElement("LoginInButton");
	            Log.info("Log Out link is found on the Home Page");
	            
	            waitForElementToBePresent("ErrorMessage");
	            Log.info("Error Message is displayed.");
	            
            }catch (Exception e){
            	Log.error("Log Out link is not found on the Home Page");
           		throw(e);
           		}      	
        }
        
}