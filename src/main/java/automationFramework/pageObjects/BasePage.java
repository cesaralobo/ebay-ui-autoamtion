package automationFramework.pageObjects;

import automationFramework.utils.GetProperties;
import automationFramework.utils.WebDriverUtils;
import automationFramework.utils.datatypes.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import static automationFramework.utils.Utils.applyDefaultIfMissing;

public abstract class BasePage {

    protected static WebDriver driver;

    protected static Actions actions;
    private static String environment = applyDefaultIfMissing(System.getProperty("environment"), "PROD");
    protected static GetProperties properties = new GetProperties(environment);


    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
        addWait(Wait.LONG_WAIT);
        actions = new Actions(BasePage.driver);
    }

    public void addWait(Wait wait){
        WebDriverUtils.addWait(this.driver, wait);
    }

    public WebElement findElement(final By locator){
        return WebDriverUtils.findElement(this.driver, locator);
    }



}
