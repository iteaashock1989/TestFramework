package com.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners extends Utils implements ITestListener {
	
	WebDriver driver;
	private static String fileSeperator = System.getProperty("file.separator");

	@Override
	public void onFailureTCImg(WebDriver driver, ITestResult getResult) {
		if(ITestResult.FAILURE == getResult.getStatus()){
					
			try {

				String testClassName = getTestClassName(getResult.getInstanceName()).trim();
				String testMethodName = getResult.getName().toString().trim();
				String screenShotName = testMethodName + ".png";

				if (driver != null) {
					String imagePath = ".." + fileSeperator + "Screenshots"
							+ fileSeperator + "Results" + fileSeperator + testClassName
							+ fileSeperator
							+ takeScreenShot(driver, testClassName, screenShotName);
					System.out.println("Screenshot can be found : " + imagePath);
				}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				}	
	}
	
	public String takeScreenShot(WebDriver driver,
			String screenShotName, String testName) throws Exception {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + testName, screenShotName);
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

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

}
