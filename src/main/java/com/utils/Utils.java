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
import org.testng.ITestResult;

public class Utils {
		
	public WebDriver getDriver;
	public String username;
	public String password;
	public String browserName;
	
	public static String fileSeperator = System.getProperty("file.separator");
	
	EnvVariablesConfigurator envVarConfig = new EnvVariablesConfigurator();
	
	public WebDriver initiateWebDriver() throws Exception{
		
		try{
			
			if(browserName.equals("Firefox")){
				
				System.setProperty("webdriver.gecko.driver", Const.Path_Drivers + Const.Gecko_Driver);
				getDriver = new FirefoxDriver();
				Log.info("Firefox driver instantiated");
			    getDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 20 seconds");	
			    
		    } else if(browserName.equals("Chrome")){	
		    	
		    	System.setProperty("webdriver.chrome.driver", Const.Path_Drivers + Const.Chrome_Driver);
				getDriver = new ChromeDriver();
				Log.info("Chrome driver instantiated");
			    getDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    Log.info("Implicit wait applied on the driver for 20 seconds");				    
		    }
			Log.info("WebDriver Inititated successfully");			
			
		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return getDriver;
	}
	
	public WebDriver launchApp(String env, String browser) throws Exception{
		
		try{			
			browserName = browser;
			initiateWebDriver();
			getDriver.manage().window().maximize();			
			username = envVarConfig.readUsername(env);
			password = envVarConfig.readPassword(env);			
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
	 
	 public void verifyElementText(String element) throws Exception{
	     
         try
         {
    		 WebDriverWait wait = new WebDriverWait(getDriver, 10);    	     
    	     wait.until(ExpectedConditions.textToBePresentInElementValue(AppTestLocatorConfigJSon.readLoc(element), "Element is present"));
    		 
         }catch (Exception e) {
        	 throw e;
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

	 public void takeScreenshotForTestCase(String testClassName, String screenShotName) throws Exception{
	 
		 try {

				if (getDriver != null) {
					String imagePath = ".." + fileSeperator + "Screenshots"
							+ fileSeperator + "Results" + fileSeperator + testClassName
							+ fileSeperator
							+ takeScreenShot(getDriver, testClassName, screenShotName);
					System.out.println("Screenshot can be found : " + imagePath);
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
	 
	 public String takeScreenShot(WebDriver driver,
				String screenShotName, String testClassName) throws Exception {
			try {
				File file = new File("Screenshots" + fileSeperator + "Results");
				if (!file.exists()) {
					System.out.println("File created " + file);
					file.mkdir();
				}
				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testClassName
						+ fileSeperator, screenShotName);
				FileUtils.copyFile(screenshotFile, targetFile);
				return screenShotName;
			} catch (Exception e) {
				Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
				throw (e);
			}
		}

		public String getTestClassName(String testName) {
			String[] reqTestClassname = testName.split("\\.");
			int i = reqTestClassname.length - 1;
			System.out.println("Required Test Name : " + reqTestClassname[i]);
			return reqTestClassname[i];
		}
		
		public void onTestFailure(ITestResult result) {
			
			if(ITestResult.FAILURE == result.getStatus()){
				
				try {
					String testClassName = getTestClassName(result.getInstanceName()).trim();
					String testMethodName = result.getName().toString().trim();
					String screenShotName = testMethodName + ".png";
					
					System.out.println("Screenshot path is  : " + screenShotName);
					takeScreenshotForTestCase(testClassName, screenShotName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
			}		
		}
	 
}