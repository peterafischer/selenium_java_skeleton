package poms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ResultPage extends BasePOM {

    String searchtext;

    @FindBy(className = "listview__item")
    List<WebElement> listview_items;

    @FindBy(className = "results__control__sort-by-select")
    WebElement sortDropdown;

    By namefieldBy = By.className("listview__name-link");
    By pricetextBy = By.className("price");

    public ResultPage(WebDriver driver, String searchstring) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.searchtext = searchstring;
    }

    public void sortByCheapest() {
        Select dropdown = new Select(sortDropdown);
        dropdown.selectByVisibleText("Preis aufsteigend");
    }

    public Float getCheapestPrice() {
        Float cheapestpricetext = null;
        for (WebElement listelement : listview_items) {
            if (listelement.findElement(namefieldBy).getText().contains(searchtext)) {
                String pricestring = listelement.findElement(pricetextBy).getText();
                Float price = Float.parseFloat(pricestring.replace(',','.').split(" ")[1]);
                if (cheapestpricetext == null || cheapestpricetext > price) {
                    cheapestpricetext = price;
                }
            }
        }
        return cheapestpricetext;
    }

}
