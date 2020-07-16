package automationFramework.tests;

import automationFramework.utils.Constants;
import automationFramework.utils.GetProperties;
import automationFramework.utils.WebDriverUtils;
import automationFramework.utils.datatypes.BrowserType;
import automationFramework.utils.datatypes.Wait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static automationFramework.utils.Utils.applyDefaultIfMissing;

public class BaseTest {

    protected static WebDriver driver;
    private static String environment = applyDefaultIfMissing(System.getProperty("environment"), "PROD");
    protected static GetProperties properties = new GetProperties(environment);
    private static String browser = properties.getString("BROWSER").toUpperCase();

    ExtentHtmlReporter htmlReports;
    protected ExtentReports extent;
    protected ExtentTest rep_test;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
    String date_str = dateFormat.format(new Date());

    String fileName = System.getProperty("user.dir") + "/test-output/" + date_str + "-results.html";

    @BeforeTest
    public void setUp(){
        htmlReports = new  ExtentHtmlReporter(fileName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReports);
        htmlReports.config().setReportName("Belatrix Exam - Automatizacion Ebay");
        htmlReports.config().setTheme(Theme.STANDARD);
        htmlReports.config().setTestViewChartLocation(ChartLocation.BOTTOM);
    }

    @BeforeMethod
    public void setUp2(Method method) {
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        DesiredCapabilities capabilities;
        FirefoxOptions fire_opt;

        switch (browserType) {
            case FIREFOX:
                fire_opt = new FirefoxOptions();
                driver = new FirefoxDriver(fire_opt);
                driver.manage().window().maximize();

                break;
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("--start-maximized");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--lang=es");

                driver = new ChromeDriver(options);

                break;
            case IE:
                capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("requireWindowFocus", false);
                capabilities.setCapability("enablePersistentHover", false);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                driver = new InternetExplorerDriver(capabilities);

                break;
            default:
                fire_opt = new FirefoxOptions();
                driver = new FirefoxDriver(fire_opt);
                driver.manage().window().maximize();
        }

        WebDriverUtils.addWait(driver, Wait.LONG_WAIT);
        navigate();

    }

    private void navigate() {
        String BASE_URL = properties.getString("BASE_URL");
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        extent.flush();
    }

    @AfterMethod
    public void checkResults(ITestResult testResults){
        if(testResults.getStatus()==ITestResult.SUCCESS){
            rep_test.log(Status.PASS, "Test Completado Exitosamente");
        }else{
            rep_test.log(Status.FAIL, "Fallo el Test !");
            rep_test.log(Status.FAIL, testResults.getThrowable());
        }
        driver.quit();
    }
}
