package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static tests.BaseTest.wait;


public class LoginPage {

    private static final By BURGER_MENU_BUTTON = By.xpath("//div[@class='q-header-profile__wrap']");
    private static final By LOGIN_BUTTON_IN_BURGER_MENU = By.xpath("//button[contains(@class, 't-btn') and contains(., 'Войти в профиль')]");
    private static final By PHONE_FIELD = By.xpath("//input[starts-with(@class, 'vti__input')]");
    private static final By CODE_FIELDS = By.xpath("div.t-modal-confirm-container > input.t-input-value.t-modal-confirm-field");
    private static final By CONFIRM_BUTTON = By.xpath("//button[@class='t-modal-confirm-submit t-btn']");
    private static final By SEND_CODE_BUTTON = By.xpath("//button[contains(normalize-space(), 'Получить код')]");
    private static final By SEND_CODE_REPEAT_BUTTON = By.xpath("//button[@class='t-btn _border-none _tertiary' and contains(text(), 'Отправить повторно')]");
    private static final By TEXT_ERROR = By.xpath("p.t-modal-confirm-error-message");
    private static final By HEADER_BEFORE_LOGIN = By.xpath("h1.q-page-home-top__title");
    private static final By CODE_LIST = By.cssSelector("tbody > tr:first-child");
    private static final By CODE = By.xpath("//body[contains(@style, 'white-space: pre-wrap')]");
    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие странички авторизации ")
    public static void open() throws InterruptedException {
        driver.get("https://flyhigh.idp.by");
        Thread.sleep(9000);
    }

    @Step("Вход в сайт  с логином  ")
    public static void login_in_site() {
        try {
            Thread.sleep(3000);
            driver.findElement(BURGER_MENU_BUTTON).click();
           // Thread.sleep(3000);
            driver.findElement(LOGIN_BUTTON_IN_BURGER_MENU).click();
           // Thread.sleep(3000);
            driver.findElement(PHONE_FIELD).sendKeys("9138449901");
            //Thread.sleep(3000);
            driver.findElement(SEND_CODE_BUTTON).click();
            String[] codeDigits = getCode();
            Thread.sleep(3000);
          List<WebElement> codeFields = wait.until(ExpectedConditions
                    .numberOfElementsToBe(CODE_FIELDS, 4));
            Thread.sleep(3000);
            for (int i = 0; i < 4; i++) {
                codeFields.get(i).clear();
                codeFields.get(i).sendKeys(codeDigits[i]);
                Thread.sleep(200); // Небольшая пауза между вводом символов
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Вход в модальном окне с логином '{user}' и паролем '{password}' ")
    public static void login_in_modal_window(String user, String password) {
        try {
            //Thread.sleep(3000);
            Robot robot = new Robot();
            typeString(robot, "dev");
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
          //  Thread.sleep(3000);
            typeString(robot, "test.dev.2023");
            //Thread.sleep(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
          //  Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getCode() throws InterruptedException {
        driver.get("http://flyhigh.idp.by:1080/");
        //Thread.sleep(3000);
        login_in_modal_window("dev", "test.dev.2023");
        driver.findElement(CODE_LIST).click();
        Thread.sleep(3000);
        String code = driver.findElement(CODE).getText();
        System.out.println(code);
        return code.split("");


    }

    private static void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException("Неизвестный символ: " + c);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }

    @Step("Проверка авторизации ")
    public static String checkAut() throws InterruptedException {
        Thread.sleep(3000);
        return driver.findElement(HEADER_BEFORE_LOGIN).getText();

    }

    @Step("Проверка текста ошибки '{textError}'")
    public static boolean isErrorTextCorrect(String textError) {
        String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(TEXT_ERROR)).getText();
        return actualText.equals(textError);
    }
}
