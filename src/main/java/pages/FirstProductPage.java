package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FirstProductPage {
    public Driver driver ;

    By blueTop = By.xpath("//div[@class=\"product-information\"]/h2");
    By category = By.xpath("(//p)[3]");
    By price = By.xpath("//span/span");
    By availability = By.xpath("//p[contains(b,\"Availability\")]");
    By condition = By.xpath("//p[contains(b,\"Condition\")]");
    By brand = By.xpath("//p[contains(b,\"Brand:\")]");


    public FirstProductPage(Driver driver) {
        this.driver = driver;
    }

    /***************************************** Assertions ****************************************/

    @Step("check That First Page Loaded Successfully")
    public FirstProductPage checkThatFirstPageLoadedSuccessfully(){
        Assert.assertTrue(driver.browser().getCurrentURL().contains("product_details/1"));
        Assert.assertTrue(driver.element().isDisplayed(blueTop));
        Assert.assertTrue(driver.element().isDisplayed(category));
        Assert.assertTrue(driver.element().isDisplayed(price));
        Assert.assertTrue(driver.element().isDisplayed(availability));
        Assert.assertTrue(driver.element().isDisplayed(condition));
        Assert.assertTrue(driver.element().isDisplayed(brand));
        return this;
    }

    @Step("check That Product Name Category Price Availability Condition Brand Values Are Visible")
    public void checkThatProductNameCategoryPriceAvailabilityConditionBrandValuesAreVisible(){
        Assert.assertEquals(driver.element().getTextOf(blueTop),"Blue Top");
        Assert.assertEquals(driver.element().getTextOf(category),"Category: Women > Tops");
        Assert.assertEquals(driver.element().getTextOf(price),"Rs. 500");
        Assert.assertEquals(driver.element().getTextOf(availability),("Availability: In Stock"));
        Assert.assertEquals(driver.element().getTextOf(condition),("Condition: New"));
        Assert.assertEquals(driver.element().getTextOf(brand),("Brand: Polo"));
    }
}
