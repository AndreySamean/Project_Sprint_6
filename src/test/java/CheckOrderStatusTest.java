import model.MainPage;
import model.settings.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


import static model.settings.Driver.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckOrderStatusTest {

    public static final String INCORRECT_ORDER_NUMBER = "hfiasgiy1";

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getWebDriver(Browser.CHROME);
        driver.get("https://qa-scooter.praktikum-services.ru/");
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
