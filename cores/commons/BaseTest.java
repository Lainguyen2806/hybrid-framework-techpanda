package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	/**
	 * @param browserName
	 * @return
	 */
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();

			// WebDriverManager.chromedriver().driverVersion("").setup();
			// WebDriverManager.chromedriver().browserVersion("").setup();
			break;

		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;

		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;

		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid!");
		}

		driver.get("http://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;

	}

	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
