package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import pages.FooterPage;
import pages.LoginPage;
import utils.PropertyReader;

import java.time.Duration;

public class BaseTest {
    public static String user = System.getProperty("user", PropertyReader.getProperty("user"));
    public static String password = System.getProperty("password", PropertyReader.getProperty("password"));
    public static WebDriverWait wait;
    public LoginPage loginPage;
    public FooterPage footerPage;
    WebDriver driver;

    @BeforeMethod
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("--headless");
            options.setCapability("unhandledPromptBehavior", "accept");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            int timeoutInSeconds = 10;
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(4));
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        } else if (browser.equalsIgnoreCase("internetexplorer")) {
            driver = new InternetExplorerDriver();
        }
        loginPage = new LoginPage(driver);
        footerPage = new FooterPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.quit();
        }
    }
}



