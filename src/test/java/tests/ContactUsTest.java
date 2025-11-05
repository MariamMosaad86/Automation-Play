package tests;

import driverfactory.Driver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import utilities.ScreenShotManager;

public class ContactUsTest {

//     WebDriver driver;

//    Driver driver;

   public ThreadLocal<Driver>driver;


    @BeforeClass
    @Parameters(value = {"browserName"})
    public void setup(@Optional("CHROME") String browserName) {
        driver=new ThreadLocal<>();
        driver.set(new Driver(browserName));
//        driver = new Driver(browserName);
        driver.get().get().navigate().to("http://automationexercise.com");
        driver.get().get().manage().window().maximize();

    }

    @Test
    public void checkThatUserCanNavigateToContactUsForm() {
        new HomePage(driver.get())
                .checkThatHomePageIsLoadedSuccessfully()
                .checkThatContactUsLinkIsDisplayed()
                .clickOnContactUsLink()
                .checkThatNavigateToContactUsPageSuccessfully()
                .fillContactUsForm()
                .clickOnChooseFileButton()
                .clickOnSubmitButton()
                .checkThatSuccessFromIsSubmittedSuccessfully()
                .clickOnHomeButton()
                .checkThatHomePageIsLoadedSuccessfully();
//        ScreenShotManager.captureScreenShot(driver.get(),"ContactUs");

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
//        driver.get().browser().deleteAllCookies();
        driver.get().get().manage().deleteAllCookies();
        driver.get().quit();
    }
}
