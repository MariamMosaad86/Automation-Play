package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static utilities.properties.PropertiesManager.webConfig;

public class ChromeDriverFactory extends DriverAbstract {


    @Override
    public WebDriver startDriver() {
        ChromeOptions options = new ChromeOptions();
        if (webConfig.getProperty("HeadlessMode").equalsIgnoreCase("TRUE")) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
        return driver;
    }

}
