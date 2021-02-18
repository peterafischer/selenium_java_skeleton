package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RootPage extends BasePOM {
    @FindBy(id="fs")
    WebElement search_field;

    @FindBy(id="gh_suchen_bt_i")
    WebElement search_button;

    public RootPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }


    public void enterSearchString(String searchitem) {
        search_field.sendKeys(searchitem);
    }

    public void pressSearch() {
        search_button.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void checkForPopup() {
        By acceptBtn = By.id("onetrust-accept-btn-handler");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(acceptBtn));
        driver.findElement(acceptBtn).click();
    }

    public void handleNotification() {
        By gbclass = By.className("gb-form");
        By button = By.id("btn btn-secondary gb-push-denied");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(gbclass)));
        driver.findElement(button).click();

    }
}
