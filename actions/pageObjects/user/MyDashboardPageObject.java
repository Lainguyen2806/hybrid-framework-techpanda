package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.LoginPageUI;
import pageUIs.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isRegisterAccountSuccessfullyMessageDisplayed(String registerSuccessfullyMessage) {
		waitForElementVisible(driver, MyDashboardPageUI.REGISTER_ACCOUNT_SUCCESSFULLY_MESSAGE);
		String actualregisterSuccessfullyMessage = getElementText(driver, MyDashboardPageUI.REGISTER_ACCOUNT_SUCCESSFULLY_MESSAGE);
		return actualregisterSuccessfullyMessage.contains(registerSuccessfullyMessage);
		
	}

	public void clickToAccountInHeaderButton() {
		waitForElementClickable(driver, MyDashboardPageUI.HEADER_ACCOUNT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.HEADER_ACCOUNT_BUTTON);
	}

	public void clickToLogoutButton() {
		waitForElementClickable(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		
	}

	public boolean isContactInfoDisplayed(String contactInfo) {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFO_TEXT);
		String actualContactInfoText = getElementText(driver, MyDashboardPageUI.CONTACT_INFO_TEXT);
		return actualContactInfoText.contains(contactInfo);
	}

}