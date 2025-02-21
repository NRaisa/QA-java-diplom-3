package org.example.model;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.example.SignInRequest;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Objects;

public class LoginPage extends Header {

    public final static String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    // локатор ссылки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    // локатор поля ввода email
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement emailInputField;

    // локатор поля ввода пароля
    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInputField;

    // локатор кнопки "Войти"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement signInButton;

    // локатор ссылки "Восстановить пароль"
    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement passwordRecoveryLink;

    public void clickRegisterLink() {registerLink.click();
    }

    public void setEmail(String email) {
        // баг с анимацией
        while (!Objects.equals(emailInputField.getValue(), email)) {
            emailInputField.shouldBe(Condition.editable).setValue(email);
        }
    }

    public void setPassword(String password) {
        passwordInputField.setValue(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public void enterPassword(String password){
        passwordInputField.sendKeys(password);
    }

    public void enterEmail(String email){
        emailInputField.sendKeys(email);
    }

    @Step("Логин пользователя")
    public void login(SignInRequest signInRequest) {
        enterEmail(signInRequest.getEmail());
        enterPassword(signInRequest.getPassword());
        clickSignInButton();
    }

    @Step("Переход на страницу восстановления пароля")
    public void clickPasswordRecoveryLink() {
        passwordRecoveryLink.click();
    }

}
