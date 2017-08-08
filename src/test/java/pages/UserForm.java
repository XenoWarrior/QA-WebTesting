package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserForm {

	@FindBy(xpath = "//input[@name='username']")
	private WebElement usernameInput;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;
	
	@FindBy(xpath = "//input[@name='FormsButton2']")
	private WebElement submitInput;
	
	/**
	 * Constructor
	 */
	public UserForm() {
		
	}
	
	/**
	 * Sends the username string to the username input box.
	 * @param user, the username
	 */
	public void sendUsername(String user) {
		this.usernameInput.click();
		this.usernameInput.sendKeys(user);
	}

	/**
	 * Sends the password string to the password input box.
	 * @param pass, the password 
	 */
	public void sendPassword(String pass) {
		this.passwordInput.click();
		this.passwordInput.sendKeys(pass);
	}
	
	/**
	 * Clicks the submit button on the page.
	 */
	public void sendForm() {
		this.submitInput.click();
	}
}
