package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import pageObjects.navigation.SideBarMyAccountPageObject;
import pageUIs.user.MyDashboardPageUI;

public class MyDashboardPageObject extends SideBarMyAccountPageObject {
	WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean isRegisterAccountSuccessfullyMessageDisplayed(String registerSuccessfullyMessage) {
		waitForElementVisible(driver, MyDashboardPageUI.REGISTER_ACCOUNT_SUCCESSFULLY_MESSAGE);
		String actualregisterSuccessfullyMessage = getElementText(driver, MyDashboardPageUI.REGISTER_ACCOUNT_SUCCESSFULLY_MESSAGE);
		return actualregisterSuccessfullyMessage.contains(registerSuccessfullyMessage);
		
	}

	public boolean isContactInfoDisplayed(String contactInfo) {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFO_TEXT);
		String actualContactInfoText = getElementText(driver, MyDashboardPageUI.CONTACT_INFO_TEXT);
		return actualContactInfoText.contains(contactInfo);
	}


	public boolean isAccountInfoSuccessfulUpdateMessageDisplayed() {
		waitForElementVisible(driver, MyDashboardPageUI.UPDATE_ACCOUNT_SUCCESSFULLY_MESSAGE);
		return isElementDisplayed(driver, MyDashboardPageUI.UPDATE_ACCOUNT_SUCCESSFULLY_MESSAGE);
	}

}
