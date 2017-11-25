package com.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageObjects.Home_Page;
import com.utils.Const;
import com.utils.Log;
import com.utils.Utils;

public class TC_001 extends Utils{	    

	private String sTestCaseId;
	//private int iTestCaseRow;
	private WebDriver getDriver;		

	@Parameters({"environment", "browser"})
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(String env, String browser) throws Exception {
		DOMConfigurator.configure("log4j.xml");
		sTestCaseId = this.toString();
		sTestCaseId = Utils.getTestCaseId(this.toString());
		Log.startTestCase(sTestCaseId);
		//ExcelUtils.setExcelFile(Const.Path_TestData + Const.File_TestData,"Sheet1");
		//iTestCaseRow = ExcelUtils.getRowContains(sTestCaseId,Const.Col_TestCaseID);
		getDriver = launchApp(env, browser);
	} 

    @Test(groups = {Const.Smoke_Test} )
  	public void verifyTC001() throws Exception {
	  try{
		  
		Home_Page homepage = new Home_Page(getDriver);
		homepage.loginApp(username, password);
		  
	
	  }catch (Exception e){
		  //ExcelUtils.setCellData("Fail", iTestCaseRow, Const.Col_Result);
		  Log.error(e.getMessage());
		  throw (e);
	  }		
  }
			
	@AfterMethod(alwaysRun = true)
  	public void afterMethod(ITestResult result) throws Exception {
	    Log.endTestCase(sTestCaseId);
	    onTestFailure(getDriver, result);	    
	    quitDriver();
  }
}