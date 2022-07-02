package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjects.user.AccountInfoPageObject;
import pageObjects.user.MyApplicationPageObject;
import pageObjects.user.MyOrderPageObject;
import pageObjects.user.MyProductReviewPageObject;
import pageUIs.navigation.SideBarMyAccountPageUI;

public class SideBarMyAccountPageObject  extends BasePage {	
	WebDriver driver;
	
	public SideBarMyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AccountInfoPageObject openAccountInfoPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.ACCOUNT_INFO_LINK);
		clickToElement(driver, SideBarMyAccountPageUI.ACCOUNT_INFO_LINK);
		return PageGeneratorManager.getAccountInfoPage(driver);
		
	}
	
	public MyOrderPageObject openMyOrderPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.MY_ORDER_LINK);
		clickToElement(driver, SideBarMyAccountPageUI.MY_ORDER_LINK);
		return PageGeneratorManager.getMyOrderPage(driver);
		
	}
	
	public MyProductReviewPageObject openMyProductReviewPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getMyProductReviewPage(driver);
	}
		
	public MyApplicationPageObject openMyApplicationPage() {
		waitForElementClickable(driver, SideBarMyAccountPageUI.MY_APPLICATION_LINK);
		clickToElement(driver, SideBarMyAccountPageUI.MY_APPLICATION_LINK);
		return PageGeneratorManager.getMyApplicationPage(driver);
	
	}
	
	public void openSideBarLinkByPageName(String pageName) {
		waitForElementClickable(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);
		clickToElement(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);
	}
	
}
