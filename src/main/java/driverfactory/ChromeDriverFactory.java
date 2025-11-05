package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ChromeDriverFactory extends DriverAbstract {


    @Override
    public WebDriver startDriver() {
        driver = new ChromeDriver();
        return driver;
    }

}
