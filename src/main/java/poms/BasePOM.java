package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePOM {
    WebDriver driver;

    public void setDriver(WebDriver driver) {this.driver = driver;}

    public BasePOM() {}

    public BasePOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
