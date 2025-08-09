package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Driver {
    private static WebDriver driver;
    private static int timeout = 5;

    private Driver() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigurationReader.getProperty("browser");
            String headless = ConfigurationReader.getProperty("headless");

            if ("chrome".equalsIgnoreCase(browser))
            {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if ("true".equalsIgnoreCase(headless))
                {
                    options.addArguments("--headless");
                }

                driver = new ChromeDriver(options);

            } else if ("firefox".equalsIgnoreCase(browser)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();

                if ("true".equalsIgnoreCase(headless))
                {
                    options.addArguments("--headless");
                }

                driver = new FirefoxDriver(options);
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver()
    {
        if (driver != null)
        {
            driver.quit();
            driver = null;
        }
    }
}

