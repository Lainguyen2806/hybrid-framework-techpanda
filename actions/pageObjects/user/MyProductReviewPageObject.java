package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.navigation.SideBarMyAccountPageObject;

public class MyProductReviewPageObject extends SideBarMyAccountPageObject {

	WebDriver driver;

	public MyProductReviewPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
