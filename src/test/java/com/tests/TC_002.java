package com.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pageObjects.Home_Page;
import com.utils.Const;
import com.utils.ExcelUtils;
import com.utils.Log;
import com.utils.Utils;

public class TC_002 extends Utils {
	
	/*
	
	private String sTestCaseId;
	private int iTestCaseRow;
	private WebDriver getDriver;
	
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  	DOMConfigurator.configure("log4j.xml");
	  	sTestCaseId = this.toString();
	  	sTestCaseId = Utils.getTestCaseId(this.toString());
		Log.startTestCase(sTestCaseId);
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseId,Constant.Col_TestCaseID);
		getDriver = OpenBrowser();
        } 

  @Test
  public void mainTest() throws Exception {
	  try{
		  
		Home_Page homepage = new Home_Page(getDriver);
		homepage.searchItem("ProductName","SearchField");
		  
		if(BaseClass.bResult==true){
			ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
		}else{
			throw new Exception("Test Case Failed because of Verification");
		}
	  }catch (Exception e){
		  ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
		  Log.error(e.getMessage());
		  throw (e);
	  }
		
  }
			
  @AfterMethod
  public void afterMethod() throws Exception {
	    Log.endTestCase(sTestCaseId);
	    quitDriver();
  		}*/
}
