package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage {

    public Driver driver;

    By signUpLoginLink = By.xpath("//a[@href=\"/login\"]");
    By logoutLink = By.xpath("//a[@href=\"/logout\"]");
    By deleteAccountLink = By.xpath("//a[@href=\"/delete_account\"]");
    By contactUsLink = By.xpath("//a[@href=\"/contact_us\"]");
    By testLink = By.xpath("(//a[@href=\"/test_cases\"])[1]");
    By productsLink = By.xpath("//a[@href=\"/products\"]");


    public HomePage(Driver driver) {
        this.driver = driver;
    }


    /***************************************** Assertions ****************************************/
    @Step("check That Logout Link Should Be Displayed")
    public HomePage checkThatLogoutLinkShouldBeDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(logoutLink));
        return this;
    }

    @Step("check That Delete Account Link Should Be Displayed")
    public HomePage checkThatDeleteAccountLinkShouldBeDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(deleteAccountLink));
        return this;
    }

    @Step("check That Login Link Should Be Displayed")
    public HomePage checkThatLoginLinkShouldBeDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(signUpLoginLink));
        return this;
    }

    @Step("check That HomePage Is Loaded Successfully")
    public HomePage checkThatHomePageIsLoadedSuccessfully() {
        Assert.assertEquals(driver.browser().getCurrentURL(), ("https://automationexercise.com/"));
        return this;
    }

    @Step("check That ContactUs Link Is Displayed")
    public HomePage checkThatContactUsLinkIsDisplayed() {
        Assert.assertTrue(driver.element().isDisplayed(contactUsLink));
        return this;
    }

    /***************************************** Actions **********************************************/

    @Step("click On SigUp Login Button")
    public LoginSignupPage clickOnSigUpLoginButton() {
        driver.element().click(signUpLoginLink);
        return new LoginSignupPage(driver);
    }

    @Step("clickOnLogOutLink")
    public LoginSignupPage clickOnLogOutLink() {
        driver.element().click(logoutLink);
        return new LoginSignupPage(driver);
    }

    @Step("clickOnDeleteAccountLink")
    public AccountSuccessfulDeletion clickOnDeleteAccountLink() {
        driver.element().click(deleteAccountLink);
        return new AccountSuccessfulDeletion(driver);
    }

    @Step("clickOnContactUsLink")
    public ContactUsPage clickOnContactUsLink() {
        driver.element().click(contactUsLink);
        return new ContactUsPage(driver);
    }

    @Step("clickOnTestLink")
    public TestPage clickOnTestLink() {
        driver.element().click(testLink);
        return new TestPage(driver);
    }

    @Step("clickOnProductsLink")
    public ProductsPage clickOnProductsLink() {
        driver.element().click(productsLink);
        return new ProductsPage(driver);
    }
}
