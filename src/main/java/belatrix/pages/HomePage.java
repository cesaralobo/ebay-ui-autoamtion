package belatrix.pages;

import automationFramework.pageObjects.BasePage;
import automationFramework.utils.Constants;
import automationFramework.utils.Utils;
import automationFramework.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

  @FindBy(how = How.CSS, using = "#gh-ac-box2 input")
  @CacheLookup
  private WebElement home_search;

  public boolean current_result;

  private static final String home_menu_item_new_w_box = "//a[contains(@href,\"ItemCondition=1000\")]";


  @FindBy(how = How.CSS, using = "#w4-w1 button")
  private WebElement home_order_button;

  @FindBy(how = How.CSS, using = "#w4-w1 .srp-sort__menu > li:nth-child(4) span")
  @CacheLookup
  private WebElement home_order_ascendant_price_button;

  @FindBy(how = How.CSS, using = "#gh-eb-Geo-a-default")
  @CacheLookup
  private WebElement home_menu_lang;

  @FindBy(how = How.CSS, using = "li[lang=\"en-US\"] > a")
  @CacheLookup
  private WebElement home_menu_lang_en_item;


  private static final String home_order_ascendant_1_5_products = "//*[@id = 'srp-river-results-listing1' or @id = 'srp-river-results-listing2' or @id = 'srp-river-results-listing3' or @id = 'srp-river-results-listing4' or @id = 'srp-river-results-listing5']";
  private static final String home_result_number = ".srp-controls__count-heading";
  private static final String home_product_price = ".s-item__price span";
  private static final String home_order_item_checked = ".srp-sort__menu-item--selected svg[class*=\"icon-check\"]";



  public HomePage(WebDriver driver) {
    super(driver);
  }

  public HomePage searchProduct(String search_keyword){
    Utils.waitForPageLoadComplete(driver);
    this.home_search.sendKeys(search_keyword);
    this.home_search.sendKeys(Keys.ENTER);
    return PageFactory.initElements(driver, HomePage.class);
  }

  public HomePage clickElement(String element_name){
    this.getWebElement(element_name).click();
    return PageFactory.initElements(driver, HomePage.class);
  }

  public HomePage checkAndPrint(String element_name){
    System.out.println("Result Number: "+this.getWebElement(element_name).getText());
    return PageFactory.initElements(driver, HomePage.class);
  }

  public HomePage orderByAscendantPrice(){

    WebDriverUtils.hover(driver,this.home_order_button);
    this.home_order_ascendant_price_button.click();

    return PageFactory.initElements(driver, HomePage.class);
  }

  public HomePage checkOrderOnFirst5Results(){

    this.current_result = false;

    String extractFloat = "[^0-9?!\\.]";

    List<WebElement> productItems = (List) Utils.waitUntilElementVisible(driver,this.home_order_ascendant_1_5_products,true);

    Float firstProdPrice = Float.valueOf(productItems.get(0).findElement(By.cssSelector(this.home_product_price)).getText().replaceAll(extractFloat,""));
    Float secondProdPrice = Float.valueOf(productItems.get(1).findElement(By.cssSelector(this.home_product_price)).getText().replaceAll(extractFloat,""));
    Float thirdProdPrice = Float.valueOf(productItems.get(2).findElement(By.cssSelector(this.home_product_price)).getText().replaceAll(extractFloat,""));
    Float fourthProdPrice = Float.valueOf(productItems.get(3).findElement(By.cssSelector(this.home_product_price)).getText().replaceAll(extractFloat,""));
    Float fifthProdPrice = Float.valueOf(productItems.get(4).findElement(By.cssSelector(this.home_product_price)).getText().replaceAll(extractFloat,""));

    System.out.println("Product Price 1:: "+fifthProdPrice);
    System.out.println("Product Price 2:: "+secondProdPrice);
    System.out.println("Product Price 3:: "+thirdProdPrice);
    System.out.println("Product Price 4:: "+fourthProdPrice);
    System.out.println("Product Price 5:: "+firstProdPrice);

    WebDriverUtils.hover(driver,this.home_order_button);

    if (driver.findElement(By.cssSelector(this.home_order_item_checked)).isDisplayed()){
      this.current_result = true;
    }

    return PageFactory.initElements(driver, HomePage.class);
  }


  private WebElement getWebElement(String element_name){

    WebElement result = null;

    switch (element_name) {
      case "New with box":
        result = (WebElement) Utils.waitUntilElementVisible(driver,this.home_menu_item_new_w_box,false);
        break;
      case "Result Number":

        result = (WebElement) Utils.waitUntilElementVisible(driver,this.home_result_number,false);
        break;
      case "Other Element":
        result = null;
        break;
    }

    return result;
  }

}
