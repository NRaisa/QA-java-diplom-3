package org.example;

import io.qameta.allure.junit4.DisplayName;

import org.example.generator.UserGenerator;
import org.example.model.*;
import org.junit.*;

import org.openqa.selenium.WebDriver;


import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


@DisplayName("Авторизация")
public class AuthorizationTest {
    private WebDriver driver;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    PasswordRecoveryPage passwordRecoveryPage;
    PersonalAccountPage personalAccountPage;
    UserRequest testUser;
    String accessToken;
    SignInRequest signInRequest;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driverRule.getDriver();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        loginPage = page(LoginPage.class);
        registrationPage = page(RegistrationPage.class);
        passwordRecoveryPage = page(PasswordRecoveryPage.class);
        personalAccountPage = page(PersonalAccountPage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testUser = UserGenerator.getUniqueUser();

        SuccessSignUp signUpResponse = ApiSteps.createUniqueUser(testUser)
                .then()
                .statusCode(200)
                .extract()
                .as(SuccessSignUp.class);
        accessToken = signUpResponse.getAccessToken();

        signInRequest = new SignInRequest(testUser.getEmail(), testUser.getPassword());
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }


    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void signInBySignInButtonOnMainPage() {
        mainPage.clickSignInButton();
        loginPage.login(UserGenerator.WORKING_EMAIL, UserGenerator.DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void signInByPersonalAccountLink() {
        mainPage.clickPersonalAccountLink();
        loginPage.login(UserGenerator.WORKING_EMAIL, UserGenerator.DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void signInBySignInButtonOnRegistrationPage() {
        mainPage.clickSignInButton();
        loginPage.clickRegisterLink();
        registrationPage.clickSignInLink();
        loginPage.login(UserGenerator.WORKING_EMAIL, UserGenerator.DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void signInBySignInButtonOnPasswordRecoveryPage() {
        mainPage.clickSignInButton();
        loginPage.clickPasswordRecoveryLink();
        passwordRecoveryPage.clickSignInLink();
        loginPage.login(UserGenerator.WORKING_EMAIL, UserGenerator.DEFAULT_PASSWORD);
        Assert.assertTrue(mainPage.checkIsCheckOutButtonEnabled());
        ApiSteps.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void signOut() {
        mainPage.clickSignInButton();
        loginPage.login(UserGenerator.WORKING_EMAIL, UserGenerator.DEFAULT_PASSWORD);
        mainPage.clickPersonalAccountLink();
        personalAccountPage.clickSignOutButton();
        loginPage.clickLogoLink();
        Assert.assertTrue(mainPage.checkIsSignInButtonEnabled());
    }
}
