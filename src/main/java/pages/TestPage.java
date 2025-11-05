package pages;

import driverfactory.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestPage {
    public Driver driver;

    By testCaseTitle = By.xpath("//h2[@class=\"title text-center\"]/b");


    public TestPage(Driver driver) {
        this.driver = driver;
    }

    /***************************************** Assertions **********************************************/
    public TestPage validateThatTestPageIsLoadedSuccessfully(){
        Assert.assertTrue(driver.browser().getCurrentURL().contains("/test_cases"));
        Assert.assertTrue(driver.element().isDisplayed(testCaseTitle));
        Assert.assertEquals(driver.element().getTextOf(testCaseTitle),"TEST CASES");
        return this ;
    }
}
