package com.projectge.QAWebTesting;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
	 * Variable definitions
	 * As a tester, you can change the username and password here.
	 * Leave the domain as http://thedemosite.co.uk/
	 */
	private static WebDriver wd; 
	
	private static String username = "tmpuser";
	private static String password = "tmppass";
	
	private static String domain = "http://thedemosite.co.uk/";
	
	/**
	 * Initialises the test case with a Chrome driver.
	 */
	@BeforeClass
	public static void beforeClassTest() {
		wd = new ChromeDriver();
	}
	
	/**
	 * Test runner method
	 */
	@Test
	public void appTest() {
		wd.manage().window().maximize();
		wd.navigate().to(domain);
		
		createAccount();
		loginAccount();

		// Status label
		WebElement status = wd.findElement(By.xpath("//blockquote/blockquote/font/center/b"));
		System.out.println(status.getText());
		
		// Check test results
		assertEquals("The login should be successful.", "**Successful Login**", status.getText());
	}
	
	/**
	 * Cleanup the Chrome process.
	 */
	@AfterClass
	public static void afterClassTest() {
		wd.quit();
	}
	
	/**
	 * Sometimes the wait method does not work, so this is a quick hack 
	 * that will definitely work because of the while(true).
	 * @param t the time in ms
	 */
	public void sleep(int t){
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	/**
	 * This method will run the related actions for creating a user.
	 */
	public void createAccount() {
		// REGISTER PAGE
		WebElement addUserButton = wd.findElement(By.xpath("//a[text()='3. Add a User']"));
		addUserButton.click();

		// Get the username box
		WebElement usernameInput = wd.findElement(By.xpath("//input[@name='username']"));
		usernameInput.click();
		usernameInput.sendKeys(username);

		// Get the password box
		WebElement passwordInput = wd.findElement(By.xpath("//input[@name='password']"));
		passwordInput.click();
		passwordInput.sendKeys(password);

		// Submit 
		WebElement submitInput = wd.findElement(By.xpath("//input[@name='FormsButton2']"));
		submitInput.click();
	}
	
	/**
	 * This method will run the related actions for logging a user in.
	 */
	private void loginAccount() {

		/// LOGIN PAGE
		WebElement loginPageButton = wd.findElement(By.xpath("//a[text()='4. Login']"));
		loginPageButton.click();

		// Get the username box
		WebElement usernameInput = wd.findElement(By.xpath("//input[@name='username']"));
		usernameInput.click();
		usernameInput.sendKeys(username);

		// Get the password box
		WebElement passwordInput = wd.findElement(By.xpath("//input[@name='password']"));
		passwordInput.click();
		passwordInput.sendKeys(password);
		
		// Get the submit button
		WebElement submitInput = wd.findElement(By.xpath("//input[@name='FormsButton2']"));
		submitInput.click();
	}
	
}
