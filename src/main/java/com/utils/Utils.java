package com.utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {
		
	public WebDriver getDriver;
	
	EnvVariablesConfigurator envVarConfig = new EnvVariablesConfigurator();
	
	public WebDriver launchApp(String env, String browser) throws Exception{
		
		try{
			
			if(browser.equals("Firefox")){
				
				System.setProperty("webdriver.gecko.driver", Const.Path_Drivers + Const.Gecko_Driver);
				getDriver = new FirefoxDriver();
				Log.info("Firefox driver instantiated");
			    getDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 20 seconds");	
			    
		    } else if(browser.equals("Chrome")){	
		    	
		    	System.setProperty("webdriver.chrome.driver", Const.Path_Drivers + Const.Chrome_Driver);
				getDriver = new ChromeDriver();
				Log.info("Chrome driver instantiated");
			    getDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 20 seconds");				    
		    }
			getDriver.manage().window().maximize();
			getDriver.get(envVarConfig.readAppUrl(env));
			Log.info("Web application launched successfully");			
			
		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return getDriver;
	}
	
	public WebDriver quitDriver() throws Exception {
		try {
			getDriver.quit();
		}
		catch (Exception e)
		{
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return getDriver;
	}
	
	public String typeText(String data, String loc) throws Exception {
		
		try {			
			getDriver.findElement(AppTestLocatorConfigJSon.readLoc(loc)).clear();
			getDriver.findElement(AppTestLocatorConfigJSon.readLoc(loc)).sendKeys(AppTestDataConfigJSon.readData(data));
			return null;			
		} catch (Exception e) {
			Log.info("Unable to type in the Field");
			throw (e);
		}
	}
	
	public void clickElement(String loc) throws Exception {
		try {			
			getDriver.findElement(AppTestLocatorConfigJSon.readLoc(loc)).click();		
		} catch (Exception e) {
			Log.info("Unable to type in the Field");
			throw (e);
		}
	}
	
	 public void mouseHoverAction(String mainElement, String subElement) throws Exception{
         
         try
         {
    		 WebElement mainElement2 = getDriver.findElement(AppTestLocatorConfigJSon.readLoc(mainElement));
    		 WebElement subElement2 = getDriver.findElement(AppTestLocatorConfigJSon.readLoc(subElement));
    		 
    		 Actions action = new Actions(getDriver);
             action.moveToElement(mainElement2).perform();
             if(subElement2.isDisplayed() == true){
            	 action.moveToElement(subElement2);
            	 Log.info(subElement2.getText() + "is found under Product Category");
             }
             action.click();
             action.perform();
             Log.info("Click action is performed on the selected Product Type");
         }catch (Exception e) {
        	 throw e;
         }
     }
	 
	 public void waitForElementToBeClickable(String element) throws Exception{
	     
         try
         {
    		 WebDriverWait wait = new WebDriverWait(getDriver, 10);
    	     wait.until(ExpectedConditions.elementToBeClickable(AppTestLocatorConfigJSon.readLoc(element)));
    		 
         }catch (Exception e) {
        	 throw e;
         }    
	 }
	 
	 public void waitForElementToBePresent(String element) throws Exception{
	     
         try
         {
    		 WebDriverWait wait = new WebDriverWait(getDriver, 10);
    	     wait.until(ExpectedConditions.presenceOfElementLocated(AppTestLocatorConfigJSon.readLoc(element)));
    		 
         }catch (Exception e) {
        	 throw e;
         }    
	 }
	 
	 public void waitForElementToBeVisible(String element) throws Exception{
	     
         try
         {
    		 WebDriverWait wait = new WebDriverWait(getDriver, 10);
    	     wait.until(ExpectedConditions.visibilityOfElementLocated(AppTestLocatorConfigJSon.readLoc(element)));
    		 
         }catch (Exception e) {
        	 throw e;
         }    
	 }
		
	 public void takeScreenshot(String sTestCaseId) throws Exception{
			try{
				File scrFile = ((TakesScreenshot)getDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(Const.Path_ScreenShot + sTestCaseId +".jpg"));	
			} catch (Exception e){
				Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
				throw (e);
			}
	}
	 
	 public static String getTestCaseId(String sTestCaseId)throws Exception{
		String value = sTestCaseId;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;
		}catch (Exception e){
		Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
		throw (e);
		}
	}
	 
}