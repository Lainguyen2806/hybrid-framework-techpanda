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

public class Level_02_Apply_Base_Page_Part_I {
	WebDriver driver;
	BasePage basePage;

	String projectPath = System.getProperty("user.dir");
	String firstName, middleName, lastName, email, password;
	String fullName = firstName + " " + lastName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		basePage = new BasePage();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		lastName = "Lai";
		firstName = "Test";
		middleName = "auto";
		email = "Laitest" + randomNumber() + "@gmail.com";
		password = "12345678";
	}

	@BeforeMethod
	public void beforeMethod() {

		basePage.openPageUrl(driver, "http://live.techpanda.org/");
		basePage.clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		basePage.sendkeyToElement(driver, "//input[@id='email']", "");
		basePage.sendkeyToElement(driver, "//input[@id='pass']", "");
		basePage.clickToElement(driver, "//button[@title='Login']");
		basePage.sleepInSecond(3);

		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-required-entry-email']"),
				"This is a required field.");
		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-required-entry-pass']"),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {

		basePage.sendkeyToElement(driver, "//input[@id='email']", "123@456.789");
		basePage.sendkeyToElement(driver, "//input[@id='pass']", "123456");
		basePage.clickToElement(driver, "//button[@title='Login']");
		basePage.sleepInSecond(3);

		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-validate-email-email']"),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_With_Incorrect_Email() {

		basePage.sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		basePage.sendkeyToElement(driver, "//input[@id='pass']", "123456");
		basePage.clickToElement(driver, "//button[@title='Login']");
		basePage.sleepInSecond(3);

		Assert.assertEquals(basePage.getElementText(driver, "//li[@class='error-msg']//span"),
				"Invalid login or password.");
	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_Invalid_Password() {
		basePage.sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		basePage.sendkeyToElement(driver, "//input[@id='pass']", "123");
		basePage.clickToElement(driver, "//button[@title='Login']");
		basePage.sleepInSecond(3);

		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-validate-password-pass']"),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {

		basePage.sendkeyToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		basePage.sendkeyToElement(driver, "//input[@id='pass']", randomNumber() + "");
		basePage.clickToElement(driver, "//button[@title='Login']");
		basePage.sleepInSecond(3);

		Assert.assertEquals(basePage.getElementText(driver, "//li[@class='error-msg']//span"),
				"Invalid login or password.");
	}

	@Test
	public void TC_06_CreateAnAccount() {
		basePage.clickToElement(driver, "//span[contains(text(),'Create an Account')]");
		basePage.sendkeyToElement(driver, "//input[@id='firstname']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='middlename']", middleName);
		basePage.sendkeyToElement(driver, "//input[@id='lastname']", lastName);
		basePage.sendkeyToElement(driver, "//input[@id='email_address']", email);
		basePage.sendkeyToElement(driver, "//input[@id='password']", password);
		basePage.sendkeyToElement(driver, "//input[@title='Confirm Password']", password);
		basePage.checkToCheckboxOrRadio(driver, "//input[@id='is_subscribed']");
		basePage.clickToElement(driver, "//button[@title='Register']");
		basePage.sleepInSecond(3);

		Assert.assertTrue(basePage.isElementDisplayed(driver, "//li[@class='success-msg']//span"));

		String contactInfo = basePage.getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p");
		Assert.assertTrue(contactInfo.contains(email));
		basePage.clickToElement(driver, "//header[@id='header']//div[@class='skip-links']/div[1]/a[1]/span[(text()='Account')]");
		basePage.clickToElement(driver, "//a[text()='Log Out']");
		basePage.sleepInSecond(5);

		String homePageTitle = basePage.getPageTitle(driver);	
		Assert.assertEquals(homePageTitle, "Home page");
	}

	@Test
	public void TC_07_LoginWithValidEmailAndPassword() {
		basePage.sendkeyToElement(driver, "//input[@id='email']", email);
		basePage.sendkeyToElement(driver, "//input[@id='pass']", password);
		basePage.clickToElement(driver, "//button[@title='Login']");	
		basePage.waitForElementVisible(driver, "//div[@class='page-title']/h1");
		
		String contactInfo = basePage.getElementText(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p");		
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
