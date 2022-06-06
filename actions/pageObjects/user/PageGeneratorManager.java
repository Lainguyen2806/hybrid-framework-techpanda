package pageObjects.user;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
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



}
