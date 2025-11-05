package pages;

import driverfactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class RegistrationSuccessPage {
    Driver driver;
    WebDriverWait wait;
    FluentWait fluentWait;


    By continueButton = By.xpath("//a[@data-qa=\"continue-button\"]");
    By successMessage = By.cssSelector("h2.title.text-center");

    public RegistrationSuccessPage(Driver driver) {
        this.driver = driver;
//        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        fluentWait = new FluentWait<>(this.driver.get())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }


    /***************************************** Assertions **********************************************/
    public RegistrationSuccessPage checkThatSuccessMessageShouldBeDisplayed() {
//        wait.until(ExpectedConditions.urlContains("/account_created"));
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/account_created"));
        Assert.assertTrue(driver.element().isDisplayed(successMessage));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(successMessage)));
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        Assert.assertEquals(driver.element().getTextOf(successMessage), "ACCOUNT CREATED!");
        return this;
    }

    /***************************************** Actions **********************************************/

    public HomePage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new HomePage(driver);
    }
}