package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;
import static tests.BaseTest.wait;

public class FooterPage {

    private static WebDriver driver;

    // Локаторы
    private static final By LOGO = By.xpath("//img[contains(@src, 'logo.svg')]");
    private static final By TEXT_COMPANY_FOOTER = By.xpath("//footer//p[@class='t-footer__desc']");
    private static final By VK_FOOTER = By.xpath("//img[@alt='ВКонтакте']");
    private static final By WHATSUPP_FOOTER = By.xpath("//img[@alt='WhatsApp']");
    private static final By NAVIGATE = By.xpath("//h4[contains(., 'Навигация')]");
    private static final By FOR_TURIST = By.xpath("//h4[contains(., 'Туристам')]");
    private static final By POPULAR_LINE = By.xpath("//h4[contains(., 'Популярные направления')]");
    private static final By NUMBER_FOOTER = By.xpath("//a[normalize-space()='+7 (499) 755-94-89']");
    private static final By ADRESS_FOOTER = By.xpath("//a[contains(., 'Сельскохозяйственная ул.')]");
    private static final By EMAIL_FOOTER = By.xpath("//a[contains(@href, 'onlinebooking@fly-high.ru')]");

    public FooterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы и скролл к футеру")
    public static void  openFooter() {
        driver.get("https://flyhigh.idp.by/");
    }

    @Step("Прокрутка страницы к футеру")
    public static void scrollToFooter() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//footer//*")));

            ((JavascriptExecutor)driver).executeScript(
                    "const footer = document.querySelector('footer');" +
                            "if (footer) {" +
                            "  footer.scrollIntoView({behavior: 'smooth', block: 'end'});" +
                            "} else {" +
                            "  window.scrollTo(0, document.body.scrollHeight);" +
                            "}");
            Thread.sleep(500);
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to footer", e);
        }
    }


    @Step("Проверка отображения логотипа")
    public static boolean isLogoDisplayed() {
        return  driver.findElement(LOGO).isDisplayed();
    }

    @Step("Проверка текста компании в футере")
    public static String isCompanyTextCorrect() {
       return driver.findElement(TEXT_COMPANY_FOOTER).getText();
    }

    @Step("Проверка наличия всех соц сетей футера")
    public static boolean checkSMME() {
        boolean flag = true;
        if (driver.findElement(FOR_TURIST).isDisplayed() == false) {
            flag = false;
        }
        if (driver.findElement(POPULAR_LINE).isDisplayed() == false) {
            flag = false;
        }
        return flag;
    }

        @Step("Проверка наличия номера в футере")
        public static String checkPhone () {
            return driver.findElement(NUMBER_FOOTER).getText();
        }

        @Step("Проверка наличия адреса в  футере")
        public static String checkAdress () {
            return driver.findElement(ADRESS_FOOTER).getText();
        }

        @Step("Проверка наличия почты в  футере")
        public static String checkEmail () {
            return driver.findElement(EMAIL_FOOTER).getText();
        }
        @Step("Проверка наличия основных блоков в   футере")
        public static boolean checkMainBlock () {
            boolean flag = true;
            if (driver.findElement(NAVIGATE).isDisplayed() == false) {
                flag = false;
            }
            if (driver.findElement(FOR_TURIST).isDisplayed() == false) {
                flag = false;
            }
            if (driver.findElement(POPULAR_LINE).isDisplayed() == false) {
                flag = false;
            }
            return flag;
        }

        @Step("Вход в модальном окне с логином '{user}' и паролем '{password}'")
        public static void loginInModalWindow (String user, String password){
            try {
                Robot robot = new Robot();
                typeString(robot, user);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.keyRelease(KeyEvent.VK_TAB);
                typeString(robot, password);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка при вводе данных через Robot", e);
            }
        }

        private static void typeString (Robot robot, String text){
            for (char c : text.toCharArray()) {
                int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
                if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                    throw new RuntimeException("Неизвестный символ: " + c);
                }
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
            }
        }
    }

