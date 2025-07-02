import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckOrderStatusTest {

    public static final String INCORRECT_ORDER_NUMBER = "hfiasgiy1";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getWebDriver(Browser.CHROME);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public static WebDriver getWebDriver(Browser browser){
        switch (browser){
            case CHROME:  return new ChromeDriver();
            case FIREFOX: return new FirefoxDriver();
            default: throw new RuntimeException("Неизвестный браузер");
        }
    }

    @Test
    public void incorrectOrderNumberGetNotFoundImageTrue(){

        MainPage mainPage = new MainPage(driver);

        mainPage.checkOrderStatus(INCORRECT_ORDER_NUMBER);
        assertTrue(mainPage.isNotFoundImageDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
