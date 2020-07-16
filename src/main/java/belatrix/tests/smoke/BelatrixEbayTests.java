package belatrix.tests.smoke;


import belatrix.pages.HomePage;
import automationFramework.tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BelatrixEbayTests extends BaseTest {

    HomePage homePage;

    @Test(description="Belatrix Exam - Ebay Search")
    public void belatrix_ebay_search_01(){

        rep_test = extent.createTest("BELATRIX_EBAY_SEARCH_01");

        try {

            //Creating Page Object
            homePage = PageFactory.initElements(driver, HomePage.class);
            rep_test.log(Status.INFO,"Setting Up Test");

            //Searching product "Shoes Puma"
            homePage.searchProduct("shoes puma");
            rep_test.log(Status.INFO,"Searching product \"Shoes Puma\"");

            //Click on New with box checkbox
            homePage.clickElement("New with box");
            rep_test.log(Status.INFO,"Click on New with box checkbox");

            //Check and print result number of products
            homePage.checkAndPrint("Result Number");
            rep_test.log(Status.INFO,"Checking and printing results of products number");

            //Order by price ascendant
            homePage.orderByAscendantPrice();
            rep_test.log(Status.INFO,"Ordering by price ascendant");

            //Assert order taking first 5 result
            homePage.checkOrderOnFirst5Results();
            rep_test.log(Status.INFO,"Asserting order taking first 5 results");
            Assert.assertTrue(homePage.current_result);
            rep_test.log(Status.PASS,"Fifth product is mayor than First one");


        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Falla - belatrix_ebay_search_01() " + e.getMessage() );
        }

    }

}
