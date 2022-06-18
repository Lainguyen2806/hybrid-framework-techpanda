package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.FooterContainerPageObject;

public class SearchTermPageObject extends FooterContainerPageObject{
	WebDriver driver;

	public SearchTermPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
