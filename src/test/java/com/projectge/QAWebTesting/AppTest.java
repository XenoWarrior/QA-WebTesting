package com.projectge.QAWebTesting;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pages.LoginStatus;
import pages.NavigationBar;
import pages.UserForm;
import utils.DataStore;
import utils.SpreadSheetReader;
import utils.TestUtilities;

import java.util.List;

/**
 * 
 * Test case for testing the website thedemosite.co.uk. Will check if an account
 * is made and an be logged into.
 * 
 * @author Administrator
 *
 */
public class AppTest {

	/**
	 * Variable definitions.
	 */
	private static WebDriver webDriver;
	
	private static NavigationBar navigationBar;
	private static UserForm userForm;
	private static LoginStatus loginStatus;

    private static ExtentReports report;
    
    private static List<String> accountDetails; 
    private static ExtentTest accountCreateTest;
    private static ExtentTest accountLoginTest;
    private static ExtentTest excelFileReaderTest;
    
    private static String reportFilePath = "C:\\Users\\Administrator\\Desktop\\Report.html";

	/**
	 * Initialises the test case with a Chrome driver.
	 */
	@BeforeClass
	public static void beforeClass() {
		webDriver = new ChromeDriver();
	}

	@Before
	public void reportSetup() {

        report = new ExtentReports();
		
        ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
        extentHtmlReporter.config().setReportName("The Demo Site");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        report.attachReporter(extentHtmlReporter);

        accountCreateTest = report.createTest("Account Creation Test");
        accountLoginTest = report.createTest("Account Login Test");
        excelFileReaderTest = report.createTest("Excel File Reader Test");

		SpreadSheetReader sheetReader;
		try {
			sheetReader = new SpreadSheetReader("TestData.xlsx");
			excelFileReaderTest.debug("Reading Excel file: TestData.xlsx");
			this.accountDetails = sheetReader.readRow(1, "Input");
			excelFileReaderTest.debug("Reading sheet: Input");

			DataStore.userName = this.accountDetails.get(2);
			DataStore.userPass = this.accountDetails.get(3);
			
			if(DataStore.userName != "" && DataStore.userPass != "") {
				excelFileReaderTest.pass("No errors occured");
			}
			else {
				excelFileReaderTest.warning("Username or password could not be read fromthe excel data sheet, using default values");
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test runner method.
	 */
	@Test
	public void appTest() {
		webDriver.navigate().to(DataStore.webDomain);
		accountCreateTest.log(Status.INFO, "Navigated to: \"" + DataStore.webDomain + "\"");
        
		// Navigation bar
		accountCreateTest.log(Status.INFO, "Attempting to click: \"3. Create a User\"");
		navigationBar = PageFactory.initElements(webDriver, NavigationBar.class);
		navigationBar.clickAddUser();

		// Account creation
		accountCreateTest.log(Status.INFO, "Sending in the username and password");
		userForm = PageFactory.initElements(webDriver, UserForm.class);
		userForm.sendUsername(DataStore.userName);
		userForm.sendPassword(DataStore.userPass);

		try {
			accountCreateTest.addScreenCaptureFromPath(TestUtilities.take(webDriver, "AccountCreateBeforeSubmit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountCreateTest.log(Status.INFO, "Sending the form");
		userForm.sendForm();

		try {
			accountCreateTest.addScreenCaptureFromPath(TestUtilities.take(webDriver, "AccountCreateAfterSubmit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountCreateTest.pass("No errors occured");
		
		// Account login
		// Since elements are "shared/same", there is no point reloading them all. 
		accountLoginTest.log(Status.INFO, "Attempting to click: \"4. Login\"");
		navigationBar.clickLogin();

		accountLoginTest.log(Status.INFO, "Sending in the username and password");
		userForm.sendUsername(DataStore.userName);
		userForm.sendPassword(DataStore.userPass);
		
		try {
			accountLoginTest.addScreenCaptureFromPath(TestUtilities.take(webDriver, "AccountLoginBeforeSubmit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		accountLoginTest.log(Status.INFO, "Sending the form");
		userForm.sendForm();
		
		accountLoginTest.pass("No errors occured");
		
		try {
			accountLoginTest.addScreenCaptureFromPath(TestUtilities.take(webDriver, "AccountLoginAfterSubmit"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get the current status message from the page
		loginStatus = PageFactory.initElements(webDriver, LoginStatus.class);
		assertEquals("The login should be successful.", "**Successful Login**", loginStatus.getMessage());

	}

	@After
	public void flushReport() {
        report.flush();
	}
	
	/**
	 * Cleanup the Chrome process.
	 */
	@AfterClass
	public static void afterClass() {
		webDriver.close();
	}

}
