package tests;

import driverfactory.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;

public class TestCasesTest {
    public Driver driver;

    @BeforeClass
    public void setup() {
        driver = new Driver("CHROME");
        driver.get().navigate().to("https://automationexercise.com/");
        driver.get().manage().window().maximize();
    }

    @Test
    public void checkThatNavigateToTestCasePageSuccessfully() {
        new HomePage(driver)
                .checkThatHomePageIsLoadedSuccessfully()
                .clickOnTestLink()
                .validateThatTestPageIsLoadedSuccessfully();
    }


    @AfterClass
    public void tearDown() {
        driver.get().manage().deleteAllCookies();
        driver.quit();
    }
}
