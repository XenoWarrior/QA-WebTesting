package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginStatus {

	@FindBy(xpath = "//blockquote/blockquote/font/center/b")
	private WebElement loginStatus;
	
	/**
	 * Fetches the text from the status message
	 */
	public String getMessage() {
		return loginStatus.getText();
	}
	
}
