package com.techpanda.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.RegisterPageObject;

public class Level_06_Page_Generator_PII extends BaseTest {
	WebDriver driver;

	UserLoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	RegisterPageObject registerPage;
	UserHomePageObject userHomePage;

	String firstName, middleName, lastName, email, password, fullName;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {	
		driver = getBrowserDriver(browserName);
		
		userHomePage = new UserHomePageObject(driver);

		lastName = "Lai";
		firstName = "Test";
		middleName = "auto";
		email = "Laitest" + randomNumber() + "@gmail.com";
		fullName = firstName + "" + middleName + "" + lastName;
		password = "12345678";

	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		loginPage =	userHomePage.openLoginPage();

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

		loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_Invalid_Password() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getPasswordInvalidErrorMessage(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {
		loginPage = userHomePage.openLoginPage();

		loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(randomNumber() + "");
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

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
