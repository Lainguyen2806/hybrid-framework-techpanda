package commons;

import org.openqa.selenium.WebDriver;

import pageObject.admin.AdminManagementCustomerPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.AccountInfoPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyApplicationPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.MyOrderPageObject;
import pageObjects.user.MyProductReviewPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchTermPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static MyDashboardPageObject getMyDashboardPage(WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
		
	public static AccountInfoPageObject getAccountInfoPage(WebDriver driver) {
		return new AccountInfoPageObject(driver);
	}
	
	public static MyOrderPageObject getMyOrderPage(WebDriver driver) {
		return new MyOrderPageObject(driver);
	}
	
	public static MyApplicationPageObject getMyApplicationPage(WebDriver driver) {
		return new MyApplicationPageObject(driver);
	}
	
	public static MyProductReviewPageObject getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}
	
	public static AboutUsPageObject getAboutUsPage(WebDriver driver) {
		return new AboutUsPageObject(driver);
	}
	
	public static SearchTermPageObject getSearchTermPage(WebDriver driver) {
		return new SearchTermPageObject(driver);
	}
	
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}

	public static AdminManagementCustomerPageObject getAdminManagementCustomerPage(WebDriver driver) {
		return new AdminManagementCustomerPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

}
