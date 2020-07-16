package automationFramework.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.rmi.CORBA.Util;

public class Utils {

  public static String applyDefaultIfMissing(String variable, String defaultValue) {
    if (variable == null) {
      variable = defaultValue;
      System.out.println("Default " + defaultValue + " execution was applied since was not provided");
    }

    return variable;
  }

  public static String today() {
    Date today = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(today);
  }

  public static String now() {
    Date today = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
    return dateFormat.format(today);
  }

  public static void waitForPageLoadComplete(WebDriver driver) {

    driver.manage().timeouts().pageLoadTimeout(Constants.TIMEOUT, TimeUnit.SECONDS);
    WebDriverWait waitElVisible = new WebDriverWait(driver,Constants.TIMEOUT);
    Wait<WebDriver> wait = new WebDriverWait(driver,Constants.PAGELOAD_TIMEOUT);
    wait.until(driver1 -> String.valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState")).equals("complete"));
    waitElVisible.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[contains(@class,\"gf-legal\")]"),"Copyright"));

  }

  public static Object waitUntilElementVisible(WebDriver driver, String locator, boolean multiElements) {
    Utils.waitForPageLoadComplete(driver);
    WebDriverWait waitElVisible = new WebDriverWait(driver,Constants.TIMEOUT);
    Object result = null;

    if (multiElements){
      result = waitElVisible.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(Utils.getLocatorBy(locator))));
    }else {
      result = waitElVisible.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(Utils.getLocatorBy(locator))));
    }
    return result;
  }
  public static WebElement waitUntilItemConditionChange(WebDriver driver, String locator) {
    Utils.waitForPageLoadComplete(driver);
    WebDriverWait waitURL = new WebDriverWait(driver,Constants.TIMEOUT);
    waitURL.until(ExpectedConditions.urlContains("LH_ItemCondition"));

    return driver.findElement(getLocatorBy(locator));
  }

  private static By getLocatorBy(String locator) {

    By byLocator = null;
    if (locator.substring(0,2).equalsIgnoreCase("//")){
      byLocator = By.xpath(locator);
    }else{
      byLocator = By.cssSelector(locator);
    }

    return byLocator;

  }

  public static void wait(int time) {
    try {
      Thread.sleep(time * 1000);
    } catch (Exception e) {

    }

  }


}

