package org.example.model;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;



public class MainPage extends Header {

    public final static String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement signInButton;

    // локатор кнопки "Оформить заказ"
    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement checkoutButton;

    @Step("Переход на страницу логина через кнопку 'Войти в аккаунт'")
    public void clickSignInButton() {
        signInButton.click();
    }

    public boolean checkIsCheckOutButtonEnabled() {
        return checkoutButton.isEnabled();
    }

    public boolean checkIsSignInButtonEnabled() {
        return signInButton.isEnabled();
    }

}


