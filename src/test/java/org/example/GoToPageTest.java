package org.example;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.example.model.LoginPage;
import org.example.model.MainPage;

import static com.codeborne.selenide.Selenide.*;


@RunWith(JUnitParamsRunner.class)
@DisplayName("Переходы на страницы")
public class GoToPageTest {

    MainPage mainPage;
    LoginPage loginPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

      @Before
    public void setUp() {
          driverRule.getDriver();
          mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
          loginPage = page(LoginPage.class);
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }


    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        Assert.assertEquals(WebDriverRunner.url(), LoginPage.LOGIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickConstructorLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        mainPage.clickPersonalAccountLink();
        loginPage.clickLogoLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
    }
}
