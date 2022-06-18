package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);

	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);

	}

	public MyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);

	}

	public String getEmailAddressEmptyErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);	
		return getElementText(driver, UserLoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE);
	}

	public String getPasswordEmptyErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);	
		return getElementText(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
	}

	public String getEmailInvalidFormatErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_INVALID_FORMAT_ERROR_MESSAGE);	
		return getElementText(driver, UserLoginPageUI.EMAIL_INVALID_FORMAT_ERROR_MESSAGE);
	}

	public String getEmailPasswordIncorrectErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_PASSWORD_INCORRECT_ERROR_MESSAGE);	
		return getElementText(driver, UserLoginPageUI.EMAIL_PASSWORD_INCORRECT_ERROR_MESSAGE);
	}

	public String getPasswordInvalidErrorMessage() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);	
		return getElementText(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);
	}

	public RegisterPageObject clickToCreateAnAccountButton() {
		waitForElementClickable(driver, UserLoginPageUI.CREATE_ACCOUNT_BUTTON);
		clickToElement(driver, UserLoginPageUI.CREATE_ACCOUNT_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}


}
