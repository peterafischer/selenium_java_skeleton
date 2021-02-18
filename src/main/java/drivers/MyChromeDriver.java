package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class MyChromeDriver {
    WebDriver driver;

    public MyChromeDriver() {
        String key = "webdriver.chrome.driver";
        String value = ".\\downloads\\chromedriver.exe";

        System.setProperty(key, value);

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        //options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--windowsize=1280,800");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
