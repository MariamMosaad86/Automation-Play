package pages;

import driverfactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginSignupPage {

    public Driver driver;


    By signUpName = By.name("name");
    By signUpEmail = By.cssSelector("input[data-qa='signup-email");
    By signUpButton = By.cssSelector("button[data-qa=\"signup-button\"]");

    By loginEmail = By.xpath("//input[@data-qa=\"login-email\"]");
    By loginPassword = By.name("password");
    By loginButton = By.xpath("//button[@data-qa=\"login-button\"]");
    By signUpFormTitle = By.xpath("(//h2)[3]");
    By errorMessageAlreadyExistEmail = By.xpath("//p[@style=\"color: red;\"]");


    public LoginSignupPage(Driver driver) {
        this.driver = driver;
    }

    /************************************ Assertions ****************************************/

    public LoginSignupPage checkThatUserIsNavigatedToLoginSignupPageSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/login"));
        Assert.assertTrue(driver.element().isDisplayed(signUpFormTitle));
        Assert.assertEquals(driver.element().getTextOf(signUpFormTitle), "New User Signup!");
        return this;
    }

    public LoginSignupPage checkThatErrorMessageIsDisplayedWhenSignUpWithExistingEmail() {
        Assert.assertTrue(driver.element().isDisplayed(errorMessageAlreadyExistEmail));
        Assert.assertEquals(driver.element().getTextOf(errorMessageAlreadyExistEmail), "Email Address already exist!");
        return this;
    }


    /************************************ Actions ****************************************/


    public LoginSignupPage fillInLoginEmail(String email) {
        driver.element().fillField(loginEmail,email);
        return this;
    }

    public LoginSignupPage fillInLoginPassword(String password) {
        driver.element().fillField(loginPassword,password);
        return this;
    }

    public HomePage clickOnLoginButton() {
        driver.element().click(loginButton);
        return new HomePage(driver);
    }


    public LoginSignupPage fillInSignUpName(String name) {
        driver.element().fillField(signUpName,name);
        return this;
    }

    public LoginSignupPage fillInSignUpEmail(String email) {
        driver.element().fillField(signUpEmail,email);
        return this;
    }

    public RegistrationPage clickOnSignUpButton() {
        driver.element().click(signUpButton);
       return new RegistrationPage(driver);


    }
    public LoginSignupPage clickOnSignUpButtonWithExistEmail() {
        driver.element().click(signUpButton);
        return this;
    }
}
