package pageObject.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.AdminManagementCustomerPageUI;

public class AdminManagementCustomerPageObject extends BasePage{
	
	WebDriver driver;
	
	public AdminManagementCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closeNotificationPopup() {
		waitForElementVisible(driver, AdminManagementCustomerPageUI.INCOMING_NOTIFICATION_POPUP);
		waitForElementVisible(driver, AdminManagementCustomerPageUI.INCOMING_NOTIFICATION_POPUP_CLOSE_ICON);
		clickToElement(driver, AdminManagementCustomerPageUI.INCOMING_NOTIFICATION_POPUP_CLOSE_ICON);	
		
	}

}
