package tests;

import driverfactory.Driver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ScreenShotManager;

public class ProductsTest {

    public Driver driver;

    @BeforeClass
    public void setUp()  {
        driver=new Driver("CHROME");
        driver.get().navigate().to("https://automationexercise.com/");
        driver.get().manage().window().maximize();
    }

    @Test
    public void checkProducts()  {
        new HomePage(driver)
                .checkThatHomePageIsLoadedSuccessfully()
                .clickOnProductsLink()
                .checkThatProductsPageLoadedSuccessfully()
                .checkThatProductsListIsVisible()
                .hoverOnFirstProduct()
                .clickOnFirstProductAddToCartButton()
                .clickOnContinueShoppingButton()
                .clickOnFirstProductViewProduct()
                .checkThatFirstPageLoadedSuccessfully()
                .checkThatProductNameCategoryPriceAvailabilityConditionBrandValuesAreVisible();
    }
//    @AfterMethod
//    public void screenshotOnFailure(ITestResult result){
//        if (result.getStatus()==ITestResult.FAILURE){
//            System.out.println("Test Failed");
//            System.out.println("Taking screenshot ....");
//            ScreenShotManager.captureScreenShot(driver.get(),result.getName());
//        }
//    }

    @AfterClass
    public void tearDown() {
        driver.browser().deleteAllCookies();
        driver.quit();
    }

}
