package tests;

import org.testng.annotations.Test;
import tests.parent.BaseTest;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(description = "проверка корректной авторизации", priority = 2)
    public void checkCorrectLogin() throws InterruptedException {
        loginPage.open();
        loginPage.loginThruZip("standard_user", "secret_sauce");
        assertTrue(productsPage.isTitlePresent());
        assertEquals(productsPage.getTitle(), "Products", "Название заголовка не соответствует ожидаемому");

        productsPage.addToCart("Sauce Labs Onesie");
        loginPage.open();
    }

    @Test(dependsOnMethods = "checkCorrectLogin", priority = 1)
    public void checkLockedUserLogin() {
        loginPage.open();
        loginPage.loginThruZip("locked_out_user", "secret_sauce");
        assertEquals(loginPage.checkErrorMsg(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
