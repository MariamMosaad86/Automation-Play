package pages;

import driverfactory.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;

public class ProductsPage {
    Driver driver;

    By allProductsTitle = By.xpath("//h2[contains(@class,\"text-center\")]");

    By productsList = By.className("features_items");
    By firstProductViewProduct = By.xpath("//a[@href=\"/product_details/1\"]");
    By firstProductOverlay = By.xpath(" (//div[@class=\"product-overlay\"])[1]");

    By firstProductAddToCartButton = By.xpath("(//a[@class=\"btn btn-default add-to-cart\"])[1]");

    By continueShoppingButton = By.xpath("//button[@data-dismiss=\"modal\"]");

    public ProductsPage(Driver driver) {
        this.driver = driver;
    }

    /***************************************** Assertions ****************************************/

    @Step("checkThatProductsPageLoadedSuccessfully")
    public ProductsPage checkThatProductsPageLoadedSuccessfully() {
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/products"));
        Assert.assertTrue(driver.element().isDisplayed(allProductsTitle));
        Assert.assertEquals(driver.element().getTextOf(allProductsTitle), "ALL PRODUCTS");
        return this;
    }

    @Step("checkThatProductsListIsVisible")
    public ProductsPage checkThatProductsListIsVisible() {
        Assert.assertTrue(driver.element().isDisplayed(productsList));
        return this;
    }

    /***************************************** Actions **********************************************/

    @Step("hoverOnFirstProduct")
    public ProductsPage hoverOnFirstProduct() {
//        driver.browser().scrollToBottom();
        driver.element().hoverOnItem(firstProductOverlay);
        return this;
    }

    @Step("clickOnFirstProductAddToCartButton")
    public ProductsPage clickOnFirstProductAddToCartButton() {
        driver.browser().scrollToBottom();
        driver.element().click(firstProductAddToCartButton);
        return this;
    }

    @Step("clickOnContinueShoppingButton")
    public ProductsPage clickOnContinueShoppingButton() {
//     Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.element().click(continueShoppingButton);
        return this;
    }

    @Step("clickOnFirstProductViewProduct")
    public FirstProductPage clickOnFirstProductViewProduct() {
       driver.browser().scrollToBottom();
        driver.element().click(firstProductViewProduct);
        return new FirstProductPage(driver);
    }


}
