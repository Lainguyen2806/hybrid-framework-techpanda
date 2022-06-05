package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.AccountInfoPageUI;

public class AccountInfoPageObject extends BasePage {
		
		WebDriver driver;
		
		public AccountInfoPageObject(WebDriver driver) {
			this.driver = driver;
		}
		

		public void inputToFirstNameTextbox(String firstName) {
			waitForElementVisible(driver, AccountInfoPageUI.UPDATE_FIRST_NAME_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.UPDATE_FIRST_NAME_TEXTBOX, firstName);
			
		}

		public void inputToLastNameTextbox(String lastName) {
			waitForElementVisible(driver, AccountInfoPageUI.UPDATE_LAST_NAME_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.UPDATE_LAST_NAME_TEXTBOX, lastName);
			
			
		}

		public void inputToEmailTextbox(String emailAddress) {
			waitForElementVisible(driver, AccountInfoPageUI.UPDATE_EMAIL_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.UPDATE_EMAIL_TEXTBOX, emailAddress);
			
			
		}

		public void inputToCurrentPasswordTextbox(String currentPassword) {
			waitForElementVisible(driver, AccountInfoPageUI.CURRENT_PASSWORD_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.CURRENT_PASSWORD_TEXTBOX, currentPassword);
			
			
		}

		public void checkOnChangePasswordCheckbox() {
			waitForElementVisible(driver, AccountInfoPageUI.CHANGE_PASSWORD_CHECKBOX);
			checkToCheckboxOrRadio(driver, AccountInfoPageUI.CHANGE_PASSWORD_CHECKBOX);
			
		}

		public void inputToNewPasswordTextbox(String newPassword) {
			waitForElementVisible(driver, AccountInfoPageUI.NEW_PASSWORD_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.NEW_PASSWORD_TEXTBOX, newPassword);
			
		}

		public void inputToNewConfirmPasswordTextbox(String confirmNewPassword) {
			waitForElementVisible(driver, AccountInfoPageUI.CONFIRM_NEW_PASSWORD_TEXTBOX);
			sendkeyToElement(driver, AccountInfoPageUI.CONFIRM_NEW_PASSWORD_TEXTBOX, confirmNewPassword);
			
		}

		public MyDashboardPageObject clickToSaveButton() {
			waitForElementClickable(driver, AccountInfoPageUI.SAVE_BUTTON);
			clickToElement(driver, AccountInfoPageUI.SAVE_BUTTON);
			return PageGeneratorManager.getMyDashboardPage(driver);
		}
	}

