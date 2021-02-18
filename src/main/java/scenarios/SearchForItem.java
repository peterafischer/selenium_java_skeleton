package scenarios;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import poms.RootPage;
import poms.ResultPage;
import util.DriverSelector;
import util.ExcelUtils;

public class SearchForItem {
    WebDriver driver;

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    //String urlLogin = "https://uat-intern.impfzentren.bayern/vaccination/";
    //String loginUser = "testuser";
    //String loginPw = "testpw";

    @BeforeTest
    @Parameters({"browser", "host", "url"})
    public void beforeSuite(String browser, String host, String url) {
        driver = DriverSelector.getDriver(host, browser, url);
    }

    @Test(priority = 1)
    @Parameters({"baseUrl"})
    public void openRootPage(String baseUrl) {
        driver.get(baseUrl);
        RootPage rootPage = new RootPage(driver);
        rootPage.checkForPopup();
        //rootPage.handleNotification();
        log.info(rootPage.getTitle());
        System.out.println(rootPage.getTitle());
        assert rootPage.getTitle().equals("Geizhals Österreich");
    }

    @Test(priority = 2, dataProvider = "SearchItems")
    //@Parameters({"devUser", "devPw"})
    public void search(String name, String amount) {
        RootPage rootPage = new RootPage(driver);
        rootPage.enterSearchString(name);
        rootPage.pressSearch();
        assert rootPage.getTitle().equals (name+" Geizhals Österreich");
    }

    @Test(priority = 3, dataProvider = "SearchItems")
    public void checkResults (String name, String amount) {
        ResultPage resultPagePage = new ResultPage(driver, name);
        resultPagePage.sortByCheapest();
        Float price = resultPagePage.getCheapestPrice();
        assert price < Float.parseFloat(amount);
    }




    @DataProvider
    public Object[][] SearchItems() throws Exception {
        Object[][] testObjArray = ExcelUtils.getTableArry("items.xlsx", "Tabelle 1", 2);
        return testObjArray;
    }

}
