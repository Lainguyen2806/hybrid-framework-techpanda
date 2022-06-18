package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.FooterContainerPageObject;

public class MyAccountPageObject extends FooterContainerPageObject{
	WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
