package org.example;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.example.generator.UserGenerator;
import org.junit.*;

import org.example.model.LoginPage;
import org.example.model.MainPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;



@DisplayName("Переходы на страницы")
public class GoToPageTest {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

      @Before
    public void setUp() {
          driverRule.getDriver();
          mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
          loginPage = page(LoginPage.class);
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      }


    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }


    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        UserRequest user = UserGenerator.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String accessToken = ApiSteps.createUniqueUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignUp.class)
                .getAccessToken();
        mainPage.clickPersonalAccountLink();
        Assert.assertEquals(WebDriverRunner.url(), LoginPage.LOGIN_PAGE_URL);
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void goToConstructorFromPersonalAccount() {
        UserRequest user = UserGenerator.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String accessToken = ApiSteps.createUniqueUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignUp.class)
                .getAccessToken();
        mainPage.clickPersonalAccountLink();
        loginPage.clickConstructorLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void goToConstructorFromPersonalAccountByLogo() {
        UserRequest user = UserGenerator.getUniqueUser();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        String accessToken = ApiSteps.createUniqueUser(user)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignUp.class)
                .getAccessToken();
        mainPage.clickPersonalAccountLink();
        loginPage.clickLogoLink();
        Assert.assertEquals(WebDriverRunner.url(), MainPage.MAIN_PAGE_URL);
        ApiSteps.deleteUser(accessToken);
    }
}
