package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AccountSuccessfulDeletion {

    private Driver driver;
    By continueButton = By.cssSelector("a.btn.btn-primary");
    By accountDeleteTitle = By.xpath("//h2[@data-qa=\"account-deleted\"]");

    public AccountSuccessfulDeletion(Driver driver) {
        this.driver = driver;
    }

    /***************************************** Assertions ****************************************/
    @Step("check That Account Should Be Deleted Successfully")
    public AccountSuccessfulDeletion checkThatAccountShouldBeDeletedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/delete_account"));
        Assert.assertTrue(driver.element().isDisplayed(accountDeleteTitle));
//        Assert.assertEquals(driver.findElement(accountDeleteTitle), "ACCOUNT DELETED!");
        return this;
    }

    /***************************************** Actions **********************************************/
    @Step("click On Continue Button")
    public HomePage clickOnContinueButton() {
        driver.element().click(continueButton);
        return new HomePage(driver);
    }
}
