package com.techpanda.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Apply_Base_Page extends BasePage {
	WebDriver driver;

	String projectPath = System.getProperty("user.dir");
	String firstName, middleName, lastName, email, password, fullName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		lastName = "Lai";
		firstName = "Test";
		middleName = "auto";
		email = "Laitest" + randomNumber() + "@gmail.com";
		fullName = firstName + " " + lastName;
		password = "12345678";
	}

	@BeforeMethod
	public void beforeMethod() {

		openPageUrl(driver, "http://live.techpanda.org/");
		clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		sendkeyToElement(driver, "//input[@id='email']", "");
		sendkeyToElement(driver, "//input[@id='pass']", "");
		clickToElement(driver, "//button[@title='Login']");
		sleepInSecond(3);

		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-email']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-pass']"),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {

		sendkeyToElement(driver, "//input[@id='email']", "123@456.789");
		sendkeyToElement(driver, "//input[@id='pass']", "123456");
		clickToElement(driver, "//button[@title='Login']");
		sleepInSecond(3);

		Assert.assertEquals(getElementText(driver, "//div[@id='advice-validate-email-email']"),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_With_Incorrect_Email() {

		sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", "123456");
		clickToElement(driver, "//button[@title='Login']");
		sleepInSecond(3);

		Assert.assertEquals(getElementText(driver, "//li[@class='error-msg']//span"),
				"Invalid login or password.");
	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_Invalid_Password() {
		sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", "123");
		clickToElement(driver, "//button[@title='Login']");
		sleepInSecond(3);

		Assert.assertEquals(getElementText(driver, "//div[@id='advice-validate-password-pass']"),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {

		sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", randomNumber() + "");
		clickToElement(driver, "//button[@title='Login']");
		sleepInSecond(3);

		Assert.assertEquals(getElementText(driver, "//li[@class='error-msg']//span"),
				"Invalid login or password.");
	}

	@Test
	public void TC_06_CreateAnAccount() {
		clickToElement(driver, "//span[contains(text(),'Create an Account')]");
		sendkeyToElement(driver, "//input[@id='firstname']", firstName);
		sendkeyToElement(driver, "//input[@id='middlename']", middleName);
		sendkeyToElement(driver, "//input[@id='lastname']", lastName);
		sendkeyToElement(driver, "//input[@id='email_address']", email);
		sendkeyToElement(driver, "//input[@id='password']", password);
		sendkeyToElement(driver, "//input[@title='Confirm Password']", password);
		checkToCheckboxOrRadio(driver, "//input[@id='is_subscribed']");
		clickToElement(driver, "//button[@title='Register']");
		sleepInSecond(3);

		Assert.assertTrue(isElementDisplayed(driver, "//li[@class='success-msg']//span"));

		String contactInfo = getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p");
		Assert.assertTrue(contactInfo.contains(email));
		clickToElement(driver, "//header[@id='header']//div[@class='skip-links']/div[1]/a[1]/span[(text()='Account')]");
		clickToElement(driver, "//a[text()='Log Out']");
		sleepInSecond(5);

		String homePageTitle = getPageTitle(driver);	
		Assert.assertEquals(homePageTitle, "Home page");
	}

	@Test
	public void TC_07_LoginWithValidEmailAndPassword() {
		sendkeyToElement(driver, "//input[@id='email']", email);
		sendkeyToElement(driver, "//input[@id='pass']", password);
		clickToElement(driver, "//button[@title='Login']");	
		waitForElementVisible(driver, "//div[@class='page-title']/h1");
		
		String contactInfo = getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p");		
		Assert.assertTrue(contactInfo.contains(email));

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
