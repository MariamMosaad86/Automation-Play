package driverfactory;

import browserActions.BrowserActions;
import elementactions.ElementActions;
import listeners.webdriver.WebDriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import static utilities.properties.PropertiesManager.webConfig;


public class Driver {

    //    private static WebDriver driver;
    private ThreadLocal<WebDriver> driver;

    public Driver(String driverType) {
        WebDriver undecoratedDriver = getDriver(driverType).startDriver();
        assert undecoratedDriver != null;

        driver = new ThreadLocal<>();
        driver.set(new EventFiringDecorator<>(org.openqa.selenium.WebDriver.class,
                new WebDriverListener(undecoratedDriver)).decorate(undecoratedDriver));
//      undecoratedDriver=new ChromeDriverFactory().startDriver();
        System.out.println("Starting the execution via "+driverType+" driver.");
        driver.get().manage().window().maximize();
        if (! webConfig.getProperty("BaseURL").isEmpty()){
            driver.get().navigate().to( webConfig.getProperty("BaseURL"));
        }

    }

    public Driver() {
        String driverType = webConfig.getProperty("BrowserType");
        WebDriver undecoratedDriver = getDriver(driverType).startDriver();
        assert undecoratedDriver != null;

        driver = new ThreadLocal<>();
        driver.set(new EventFiringDecorator<>(org.openqa.selenium.WebDriver.class,
                new WebDriverListener(undecoratedDriver)).decorate(undecoratedDriver));
//      undecoratedDriver=new ChromeDriverFactory().startDriver();
        System.out.println("Starting the execution via "+driverType+" driver.");
        driver.get().manage().window().maximize();
        if (! webConfig.getProperty("BaseURL").isEmpty()){
            driver.get().navigate().to( webConfig.getProperty("BaseURL"));
        }
    }


    private DriverAbstract getDriver(String driver) {
        switch (driver) {
            case "CHROME":
                return new ChromeDriverFactory();
            case "FIREFOX":
                return new FireFoxDriverFactory();
            case "EDGE":
                return new EdgeDriverFactory();
            default:
                throw new IllegalStateException("Unexpected value" + driver);
        }
    }


    public WebDriver get() {
        return driver.get();
    }

    public void quit() {
        driver.get().quit();
        driver.remove();
    }

    public ElementActions element() {
        return new ElementActions(driver.get());
    }

    public BrowserActions browser() {
        return new BrowserActions(driver.get());
    }
}
