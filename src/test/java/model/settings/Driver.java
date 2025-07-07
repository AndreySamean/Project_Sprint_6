package model.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

    public static WebDriver getWebDriver(Browser browser){
        switch (browser){
            case CHROME:  return new ChromeDriver();
            case FIREFOX: return new FirefoxDriver();
            default: throw new RuntimeException("Неизвестный браузер");
        }
    }
}
