package tests;

import com.github.javafaker.Faker;
import driverfactory.Driver;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public class NewTest {
    Driver driver;
    Faker faker;
    String email;
    String password;


    //start driver
    @BeforeClass
    public void setup() {
        driver = new Driver("CHROME");
        faker = new Faker();

        driver.get().navigate().to("https://automationexercise.com/");
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test(priority = 1)
    public void userCanRegisterSuccessfully() {
        email = faker.internet().safeEmailAddress();
        password = faker.internet().emailAddress();

        new HomePage(driver)
                .checkThatHomePageIsLoadedSuccessfully()
                .clickOnSigUpLoginButton()
                .checkThatUserIsNavigatedToLoginSignupPageSuccessfully()
                .fillInSignUpName("Mariam")
                .fillInSignUpEmail(email)
                .clickOnSignUpButton()
                .checkThatSignUpPageIsLoadedSuccessfully()
                .fillInRegistrationForm(password)
                .clickOnSignUpButton()
                .checkThatSuccessMessageShouldBeDisplayed()
                .clickOnContinueButton();
        driver.get().manage().deleteAllCookies();
    }

    @Test(priority = 2, dependsOnMethods = "userCanRegisterSuccessfully")
    public void userCanLoginSuccessfully() {
        driver.get().navigate().to("https://automationexercise.com/");
        new HomePage(driver).clickOnSigUpLoginButton()
                .checkThatUserIsNavigatedToLoginSignupPageSuccessfully()
                .fillInLoginEmail(email)
                .fillInLoginPassword(password)
                .clickOnLoginButton()
                .checkThatLogoutLinkShouldBeDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = "userCanLoginSuccessfully")
    public void userCanLogOutSuccessfully() {
        new HomePage(driver)
                .checkThatLogoutLinkShouldBeDisplayed()
                .clickOnLogOutLink()
                .checkThatUserIsNavigatedToLoginSignupPageSuccessfully();
    }

    @Test(priority = 4, dependsOnMethods = "userCanLogOutSuccessfully")
    public void userCannotSignInWithExistingEmail() {
        driver.get().navigate().to("https://automationexercise.com/");
        new HomePage(driver)
                .checkThatHomePageIsLoadedSuccessfully()
                .clickOnSigUpLoginButton()
                .checkThatUserIsNavigatedToLoginSignupPageSuccessfully()
                .fillInSignUpName("Mariam")
                .fillInSignUpEmail(email)
                .clickOnSignUpButtonWithExistEmail()
                .checkThatErrorMessageIsDisplayedWhenSignUpWithExistingEmail();
    }

    @Test(priority = 5, dependsOnMethods = "userCannotSignInWithExistingEmail")
    public void userCanDeleteSuccessfully() {
        new LoginSignupPage(driver)
                .fillInLoginEmail(email)
                .fillInLoginPassword(password)
                .clickOnLoginButton()
                .checkThatDeleteAccountLinkShouldBeDisplayed()
                .clickOnDeleteAccountLink()
                .checkThatAccountShouldBeDeletedSuccessfully()
                .clickOnContinueButton()
                .checkThatHomePageIsLoadedSuccessfully()
                .checkThatLoginLinkShouldBeDisplayed();
    }

    //kill driver
    @AfterClass
    public void tearDown() {
        driver.get().manage().deleteAllCookies();
        driver.quit();
    }
}
