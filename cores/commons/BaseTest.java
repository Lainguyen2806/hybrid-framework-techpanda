package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
			break;

		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name is not valid!");
		}

		driver.get("http://live.techpanda.org/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		return driver;

	}
}
