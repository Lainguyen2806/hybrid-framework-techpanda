package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.SearchTermPageObject;
import pageUIs.navigation.FooterContainerPageUI;
import pageUIs.navigation.SideBarMyAccountPageUI;

public class FooterContainerPageObject extends BasePage {
	WebDriver driver;

	public FooterContainerPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public AboutUsPageObject openAboutUsPage() {
		waitForElementClickable(driver, FooterContainerPageUI.ABOUT_US_LINK);
		clickToElement(driver, FooterContainerPageUI.ABOUT_US_LINK);
		return PageGeneratorManager.getAboutUsPage(driver);
		
	}
	
	public SearchTermPageObject openSearchTermPage() {
		waitForElementClickable(driver, FooterContainerPageUI.SEARCH_TERM_LINK);
		clickToElement(driver, FooterContainerPageUI.SEARCH_TERM_LINK);
		return PageGeneratorManager.getSearchTermPage(driver);
		
	}
	
	public MyAccountPageObject openMyAccountPage() {
		waitForElementClickable(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
		
	}
	
	public void openFooterLinkByPageName(String pageName) {
		waitForElementClickable(driver, FooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageName);
		clickToElement(driver, FooterContainerPageUI.DYNAMIC_FOOTER_LINK, pageName);
	}

}
