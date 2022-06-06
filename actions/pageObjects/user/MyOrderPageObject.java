package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class MyOrderPageObject extends BasePage {

	WebDriver driver;

	public MyOrderPageObject(WebDriver driver) {
		this.driver = driver;
	}
}