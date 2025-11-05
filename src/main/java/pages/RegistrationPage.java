package pages;

import com.github.javafaker.Faker;
import driverfactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class RegistrationPage {

    public Driver driver;

    By MrsGender = By.id("id_gender2");
    By singUpPassword = By.id("password");
    By days = By.id("days");
    By months = By.id("months");
    By years = By.id("years");
    By SignUpFirstName = By.id("first_name");
    By signUpLastName = By.id("last_name");
    By signUpAddress = By.id("address1");
    By country = By.id("country");
    By SinUpState = By.id("state");
    By SignUpCity = By.id("city");
    By signUpZipCode = By.id("zipcode");
    By signUpPhoneNumber = By.id("mobile_number");
    By signUpButton = By.xpath("//button[@data-qa=\"create-account\"]");
    By signUpFormTitle = By.xpath("(//b)[1]");


    public RegistrationPage(Driver driver) {
        this.driver = driver;
    }

    /***************************************** Assertions **********************************************/

    public RegistrationPage checkThatSignUpPageIsLoadedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/signup"));
        Assert.assertEquals(driver.element().getTextOf(signUpFormTitle), "ENTER ACCOUNT INFORMATION");
        return this;
    }

    /***************************************** Actions **********************************************/


    Faker faker = new Faker();

    public RegistrationPage fillInRegistrationForm(String password) {
        driver.element().click(MrsGender);
        driver.element().fillField(singUpPassword, password);
        driver.element().selectByIndex(days, 4);
        driver.element().selectByVisibleText(months, "October");
        driver.element().selectByValue(years, "1986");
        driver.element().fillField(SignUpFirstName, faker.name().firstName());
        driver.element().fillField(signUpLastName, faker.name().lastName());
        driver.element().fillField(signUpAddress, faker.address().country());
        driver.element().selectByIndex(country, faker.number().numberBetween(0, 6));
        driver.element().fillField(SinUpState, faker.address().state());
        driver.element().fillField(SignUpCity, faker.address().city());
        driver.element().fillField(signUpZipCode, faker.country().countryCode2());
        driver.element().fillField(signUpPhoneNumber, faker.phoneNumber().cellPhone());
        return this;
    }

    public RegistrationSuccessPage clickOnSignUpButton() {
        driver.element().click(signUpButton);
        return new RegistrationSuccessPage(driver);
    }
}
