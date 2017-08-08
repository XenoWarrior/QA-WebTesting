package com.projectge.QAWebTesting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import pages.LoginStatus;
import pages.NavigationBar;
import pages.UserForm;
import utils.DataStore;
import utils.HackyMethods;

/**
 * 
 * Test case for testing the website thedemosite.co.uk.
 * Will check if an account is made and an be logged into.
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

	/**
	 * Initialises the test case with a Chrome driver.
	 */
	@BeforeClass
	public static void beforeClass() {
		webDriver = new ChromeDriver();
	}

	/**
	 * Test runner method.
	 */
	@Test
	public void appTest() {
		webDriver.navigate().to(DataStore.webDomain);

		// Account creation
		navigationBar = PageFactory.initElements(webDriver, NavigationBar.class);
		navigationBar.clickAddUser();
		userForm = PageFactory.initElements(webDriver, UserForm.class);
		userForm.sendUsername(DataStore.userName);
		userForm.sendPassword(DataStore.userPass);
		userForm.sendForm();
		
		// Account login
		// Since elements are "shared/same", there is no point reloading them all. 
		navigationBar.clickLogin();
		userForm.sendUsername(DataStore.userName);
		userForm.sendPassword(DataStore.userPass);
		userForm.sendForm();
		
		loginStatus = PageFactory.initElements(webDriver, LoginStatus.class);

		assertEquals("The login should be successful.", "**Successful Login**", loginStatus.getMessage());
	}
	
	/**
	 * Cleanup the Chrome process.
	 */
	@AfterClass
	public static void afterClass() {
		webDriver.close();
	}
	
	
}
