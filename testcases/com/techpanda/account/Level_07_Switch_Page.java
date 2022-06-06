package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.AccountInfoPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyApplicationPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.MyOrderPageObject;
import pageObjects.user.MyProductReviewPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.RegisterPageObject;

public class Level_07_Switch_Page extends BaseTest {
	WebDriver driver;

	HomePageObject homePage;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	RegisterPageObject registerPage;
	AccountInfoPageObject accountInfoPage;
	MyOrderPageObject myOrderPage;
	MyApplicationPageObject myApplicationPage;
	MyProductReviewPageObject myProductReviewPage;

	String firstName, middleName, lastName, email, password, fullName, newPassword;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {	
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getHomePage(driver);

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
		loginPage =	homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("123@456.789");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailInvalidFormatErrorMessage(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Incorrect_Email() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_Invalid_Password() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(String.valueOf(getRandomNumber()));
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
	}

	@Test
	public void TC_06_CreateAnAccount() {
		loginPage = homePage.openLoginPage();
		
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

		myDashboardPage.clickToAccountInHeaderButton();
		homePage = myDashboardPage.clickToLogoutButton();

	}

	@Test
	public void TC_07_LoginWithValidEmailAndPassword() {
		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox(email);
		loginPage.inputToPasswordTextbox(password);
		myDashboardPage = loginPage.clickToLoginButton();
		
		myDashboardPage.sleepInSecond(5);
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(fullName));
		Assert.assertTrue(myDashboardPage.isContactInfoDisplayed(email));

	}
	
	@Test
	public void TC_08_UpdateAccountInfo() {
		accountInfoPage = myDashboardPage.openAccountInfoPage(driver);
		
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
		myOrderPage = myDashboardPage.openMyOrderPage(driver);
		
		myApplicationPage = myOrderPage.openMyApplicationPage(driver);
		
		myProductReviewPage = myApplicationPage.openMyProductReviewPage(driver);			

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
