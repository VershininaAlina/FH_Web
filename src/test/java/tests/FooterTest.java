package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FooterPage;

import static pages.FooterPage.isCompanyTextCorrect;


public class FooterTest extends BaseTest {

    @Test(testName = "Проверка наличия логотипа в футере",
            description = "Проверка наличия логотипа в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_logo_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertTrue(FooterPage.isLogoDisplayed());
    }

    @Test(testName = "Проверка отображения текста компании в футере",
            description = "Проверка отображения текста компании в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_text_company_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertEquals(isCompanyTextCorrect(), "Подбор, бронирование и продажа туров от крупнейших туроператоров России — по вашим предпочтениям");
    }

    @Test(testName = "Проверка наличия соц сетей в футере",
            description = "Проверка наличия соц сете в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_social_service_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertTrue(FooterPage.checkSMME());
    }

    @Test(testName = "Проверка наличия номера в футере",
            description = "Проверка наличия номера в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_phone_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertEquals(FooterPage.checkPhone(), "+7 (499) 755-94-89");
    }

    @Test(testName = "Проверка наличия адреса в футере",
            description = "Проверка наличия адреса в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_adress_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertEquals(FooterPage.checkAdress(), "129226, Москва, Сельскохозяйственная ул., д.30, ст.1, пом. 19");
    }

    @Test(testName = "Проверка наличия почты в футере",
            description = "Проверка наличия почты в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_email_footers() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertEquals(FooterPage.checkEmail(), "onlinebooking@fly-high.ru");
    }

    @Test(testName = "Проверка наличия основных блоков в футере",
            description = "Проверка наличия основных блоков в футере")
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Footer")
    @Feature("Footer")
    @Story("Footer")
    public void check_main_block() throws InterruptedException {
        FooterPage.openFooter();
        FooterPage.loginInModalWindow("dev", "test.dev.2023");
        FooterPage.scrollToFooter();
        Assert.assertEquals(FooterPage.checkMainBlock(), true);
    }



}
