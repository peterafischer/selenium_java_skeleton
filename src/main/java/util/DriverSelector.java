package util;

import drivers.MyChromeDriver;
import drivers.MyFirefoxDriver;
import org.openqa.selenium.WebDriver;

public class DriverSelector {
    public static WebDriver getDriver(String host, String browser, String hub) {
        switch (host) {
            case "local":
                switch (browser) {
                    case "chrome": return new MyChromeDriver().getDriver();
                    case "firefox": return new MyFirefoxDriver().getDriver();
                }
                break;
        }
        return null;
    }
}
