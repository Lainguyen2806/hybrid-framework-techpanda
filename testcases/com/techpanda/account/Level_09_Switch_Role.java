package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.admin.AdminManagementCustomerPageObject;
import pageObject.admin.AdminLoginPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.RegisterPageObject;

public class Level_09_Switch_Role extends BaseTest {
	WebDriver driver;

	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	AdminManagementCustomerPageObject adminManageCustomerPage;
	AdminLoginPageObject adminLoginPage;
	MyDashboardPageObject myDashboardPage;
	RegisterPageObject registerPage;
	

	String firstName, middleName, lastName, email, password, fullName, newPassword, userURL, adminURL;

	@Parameters({ "browser", "userURL", "adminURL" })
	@BeforeClass
	public void beforeClass(String browserName, String userURL, String adminURL) {
		this.userURL = userURL;
		this.adminURL = adminURL;
		
		driver = getBrowserDriver(browserName, userURL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		lastName = "Lai";
		firstName = "Test";
		middleName = "auto";
		email = "Laitest" + getRandomNumber() + "@gmail.com";
		fullName = firstName + " " + middleName + " " + lastName;
		password = "12345678";
		newPassword = "123456a@";

	}
	@Test
	public void TC_01_CreateAnAccount() {
		userLoginPage = userHomePage.openLoginPage();

		registerPage = userLoginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToMiddleNameTextbox(middleName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		registerPage.checkOnSignUpCheckbox();
		myDashboardPage = registerPage.clickToRegisterButton();

		Assert.assertTrue(myDashboardPage
				.isRegisterAccountSuccessfullyMessageDisplayed("Thank you for registering with Main Website Store."));
		
		userHomePage = myDashboardPage.clickToLogoutLinkWithUser(driver);

	}

	@Test
	public void TC_02_LoginWithValidEmailAndPassword() {
		userLoginPage = userHomePage.openLoginPage();

		userLoginPage.inputToEmailAddressTextbox(email);
		userLoginPage.inputToPasswordTextbox(password);
		myDashboardPage = userLoginPage.clickToLoginButton();

		myDashboardPage.sleepInSecond(5);
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(fullName));
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(email));	
		
		userHomePage = myDashboardPage.clickToLogoutLinkWithUser(driver);

	}

	@Test
	public void TC_03_Switch_Role() {
		// User --> Admin
		adminLoginPage = myDashboardPage.openAdminLoginPage(driver, adminURL);
		
		adminLoginPage.inputToUserNameTextbox("user01");
		adminLoginPage.inputToPasswordTextbox("guru99com");
		adminManageCustomerPage = adminLoginPage.clickToLoginButton();
		
		adminManageCustomerPage.closeNotificationPopup();

		// Admin --> User
		adminLoginPage = adminManageCustomerPage.clickToLogoutLinkWithAdmin(driver);	
		userHomePage = adminLoginPage.openUserHomePage(driver, userURL);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
