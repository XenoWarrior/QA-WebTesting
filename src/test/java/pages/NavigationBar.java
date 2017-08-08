package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NavigationBar {

	@FindBy(xpath = "//a[@href='addauser.php']")
	private WebElement addUserButton;
	
	@FindBy(xpath = "//a[@href='login.php']")
	private WebElement loginButton;
	
	/**
	 * Constructor
	 */
	public NavigationBar() {
		
	}

	/**
	 * Clicks the add a user button on the page.
	 */
	public void clickAddUser() {
		addUserButton.click();
	}
	
	/**
	 * Clicks the login button on the page.
	 */
	public void clickLogin() {
		loginButton.click();
	}
	
}
