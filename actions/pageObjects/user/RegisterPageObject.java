package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);

	}

	public void inputToMiddleNameTextbox(String middleName) {
		waitForElementVisible(driver, RegisterPageUI.MIDDLE_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.MIDDLE_NAME_TEXTBOX, middleName);

	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);

	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_ADDRESS_TEXTBOX, email);

	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);


	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);


	}

	public void checkOnSignUpCheckbox() {
		waitForElementVisible(driver, RegisterPageUI.SIGN_UP_CHECKBOX);
		checkToCheckboxOrRadio(driver, RegisterPageUI.SIGN_UP_CHECKBOX);

	}

	public MyDashboardPageObject clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getMyDashboardPage(driver);
		

	}


}
