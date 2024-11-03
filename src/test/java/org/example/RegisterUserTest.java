package org.example;

import io.qameta.allure.junit4.DisplayName;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.runner.RunWith;
import org.example.model.LoginPage;
import org.example.model.MainPage;
import org.example.model.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

import static org.example.generator.UserGenerator.*;

@RunWith(JUnitParamsRunner.class)
@DisplayName("Регистрация")
public class RegisterUserTest {

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
