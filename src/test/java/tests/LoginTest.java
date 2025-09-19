package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tests.parent.BaseTest;

import java.time.Duration;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void checkErrorLogin() {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5));
        loginPage.open();
        loginPage.loginThruZip("1");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".error_message")));
        String errorMsg = browser.findElement(By.cssSelector(".error_message")).getText();
        assertEquals(errorMsg, "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void checkLogin() {
        loginPage.open();
        loginPage.loginThruZip("12345");

        boolean isPresent = browser.findElement(By.xpath("//*[text()='Sign Up']")).isDisplayed();
        assertTrue(isPresent);
    }
}
