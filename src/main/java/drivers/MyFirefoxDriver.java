package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class MyFirefoxDriver {
    WebDriver driver;

    public MyFirefoxDriver() {
        String key = "webdriver.gecko.driver";
        String value = ".\\downloads\\geckodriver.exe";

        System.setProperty(key, value);

        FirefoxOptions options = new FirefoxOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        //options.addArguments("--no-sandbox");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--windowsize=1280,800");

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
