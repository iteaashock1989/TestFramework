package com.pageObjects;

import org.openqa.selenium.WebDriver;

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
        
}