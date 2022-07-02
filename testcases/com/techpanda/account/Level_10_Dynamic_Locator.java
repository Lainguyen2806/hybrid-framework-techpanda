package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.AccountInfoPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyApplicationPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.MyOrderPageObject;
import pageObjects.user.MyProductReviewPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.SearchTermPageObject;

public class Level_10_Dynamic_Locator extends BaseTest {
	WebDriver driver;

	UserHomePageObject userHomePage;
	UserLoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	RegisterPageObject registerPage;
	AccountInfoPageObject accountInfoPage;
	MyOrderPageObject myOrderPage;
	MyApplicationPageObject myApplicationPage;
	MyProductReviewPageObject myProductReviewPage;
	AboutUsPageObject aboutUsPage;
	SearchTermPageObject searchTermPage;
	MyAccountPageObject myAccountPage;

	String firstName, middleName, lastName, email, password, fullName, newPassword;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

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
	public void TC_01_Login_With_Empty_Email_And_Password() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("123@456.789");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailInvalidFormatErrorMessage(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Incorrect_Email() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_Invalid_Password() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(String.valueOf(getRandomNumber()));
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_CreateAnAccount() {
		loginPage = userHomePage.openLoginPage();

		registerPage = loginPage.clickToCreateAnAccountButton();

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
	public void TC_07_LoginWithValidEmailAndPassword() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		myDashboardPage = loginPage.clickToLoginButton();

		myDashboardPage.sleepInSecond(5);
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(fullName));
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(email));

	}

	@Test
	public void TC_08_UpdateAccountInfo() {		
		myDashboardPage.openSideBarLinkByPageName("Account Information");
		accountInfoPage = PageGeneratorManager.getAccountInfoPage(driver);

		accountInfoPage.inputToFirstNameTextbox("Test");
		accountInfoPage.inputToLastNameTextbox("Automation");
		accountInfoPage.inputToEmailTextbox("auto_test" + getRandomNumber() + "@live.com");
		accountInfoPage.inputToCurrentPasswordTextbox(password);
		accountInfoPage.checkOnChangePasswordCheckbox();
		accountInfoPage.inputToNewPasswordTextbox(newPassword);
		accountInfoPage.inputToNewConfirmPasswordTextbox(newPassword);
		myDashboardPage = accountInfoPage.clickToSaveButton();

		myDashboardPage.sleepInSecond(5);
		Assert.assertTrue(myDashboardPage.isAccountInfoSuccessfulUpdateMessageDisplayed());

	}

	@Test
	public void TC_09_SwitchPage() {
		//myOrderPage = myDashboardPage.openMyOrderPage();
		myDashboardPage.openSideBarLinkByPageName("My Orders");
		myOrderPage = PageGeneratorManager.getMyOrderPage(driver);
		
		myOrderPage.openSideBarLinkByPageName("My Applications");
		myApplicationPage = PageGeneratorManager.getMyApplicationPage(driver);
		
		myApplicationPage.openSideBarLinkByPageName("My Product Reviews");
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);

	}

	@Test
	public void TC_10_Footer_Navigation() {
		myProductReviewPage.getFooterContainerPage(driver).openFooterLinkByPageName("About Us");
		aboutUsPage = PageGeneratorManager.getAboutUsPage(driver);
		
		aboutUsPage.openFooterLinkByPageName("Search Terms");
		searchTermPage = PageGeneratorManager.getSearchTermPage(driver);
		
		searchTermPage.openFooterLinkByPageName("My Account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
