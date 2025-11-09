package pages;

import com.github.javafaker.Faker;
import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class ContactUsPage {

    public Driver driver;

    By name = By.name("name");
    By email = By.name("email");
    By subject = By.name("subject");
    By message = By.id("message");
    By submitButton = By.name("submit");
    By getInTouchTitle = By.xpath("(//h2[@class=\"title text-center\"])[2]");  // هنا استخدمت contains
    By successMessage = By.xpath("(//div[contains(@class,\"alert-success\")])[1]");
    By homeButton = By.xpath("//a[@class=\"btn btn-success\"]");
    By chooseFileButton = By.xpath("(//input[@class=\"form-control\"])[4]");

    Faker faker = new Faker();

    public ContactUsPage(Driver driver) {
        this.driver = driver;
    }


    /***************************************** Assertions **********************************************/
    @Step("check That Navigate To ContactUs Page Successfully")
    public ContactUsPage checkThatNavigateToContactUsPageSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/contact_us"));
        Assert.assertTrue(driver.element().isDisplayed(getInTouchTitle));
        Assert.assertEquals(driver.element().getTextOf(getInTouchTitle), "GET IN TOUCH");
        return this;
    }

    @Step("check That Success From I sSubmitted Successfully")
    public ContactUsPage checkThatSuccessFromIsSubmittedSuccessfully() {
        Assert.assertTrue(driver.element().isDisplayed(successMessage));
        Assert.assertEquals(driver.element().getTextOf(successMessage), "Success! Your details have been submitted successfully.");
        return this;
    }

    /***************************************** Actions **********************************************/

    @Step("fill ContactUs Form")
    public ContactUsPage fillContactUsForm() {
        driver.element().fillField(name, faker.name().fullName());
        driver.element().fillField(email, faker.internet().emailAddress());
        driver.element().fillField(subject, "Test");
        driver.element().fillField(message, "Test");
        return this;

    }

    // upload file
    @Step("click On Choose File Button")
    public ContactUsPage clickOnChooseFileButton() {
        WebElement uploadInput = driver.get().findElement(chooseFileButton);
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Screenshot_1.png";
        uploadInput.sendKeys(filePath);
//        driver.findElement(chooseFileButton).sendKeys(filePath);
        return this;
    }

    @Step("clickOnSubmitButton")
    public ContactUsPage clickOnSubmitButton() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.element().click(submitButton);
        driver.get().switchTo().alert().accept();
        return this;
    }

    @Step("clickOnHomeButton")
    public HomePage clickOnHomeButton() {
        driver.element().click(homeButton);
        return new HomePage(driver);
    }


}
