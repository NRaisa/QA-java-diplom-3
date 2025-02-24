package org.example;

import io.qameta.allure.junit4.DisplayName;

import org.junit.*;

import org.example.model.ConstructorPage;
import org.example.model.MainPage;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;



@DisplayName("Вкладки конструктора")
public class SectionsTest {
    private WebDriver driver;
    MainPage mainPage;
    ConstructorPage constructorPage;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp() {
        driverRule.getDriver();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        constructorPage = page(ConstructorPage.class);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(){
        clearBrowserLocalStorage();
    }

    @Test
    public void bunTabValidWorking() {
        Assert.assertTrue(constructorPage.checkIsBunTabSelected());
    }

    @Test
    public void sauceTabValidWorking() {
        constructorPage.clickSauceTab();
        Assert.assertTrue(constructorPage.checkIsSauceTabSelected());
    }

    @Test
    public void fillingTabValidWorking() {
        constructorPage.clickFillingTab();
        Assert.assertTrue(constructorPage.checkIsFillingTabSelected());
    }
}
