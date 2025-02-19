package org.example;

import io.qameta.allure.junit4.DisplayName;

import org.junit.*;

import org.example.model.LoginPage;
import org.example.model.MainPage;
import org.example.model.RegistrationPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

import static org.example.generator.UserGenerator.*;


@DisplayName("Регистрация")
public class RegisterUserTest {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    String newEmail;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driverRule.getDriver();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.clickSignInButton();

        loginPage = page(LoginPage.class);
        loginPage.clickRegisterLink();

        registrationPage = page(RegistrationPage.class);
        newEmail = getNewRandomEmail();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }


    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registerUserSuccessfully() {
        registrationPage.register(DEFAULT_NAME, newEmail, DEFAULT_PASSWORD);
        loginPage.login(newEmail, DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
    }

    @Test
    @DisplayName("Регистрация со слишком коротким паролем")
    public void registerUserWithShortPassword() {
        registrationPage.register(DEFAULT_NAME, newEmail, SHORT_PASSWORD);
        Assert.assertTrue(registrationPage.checkIsIncorrectPasswordTextVisible());
    }
}
