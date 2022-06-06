package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class MyApplicationPageObject extends BasePage {

	WebDriver driver;

	public MyApplicationPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
