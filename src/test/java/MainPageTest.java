import model.settings.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import model.MainPage;


import java.util.Set;

import static model.settings.Driver.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = getWebDriver(Browser.CHROME);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }


    @ParameterizedTest
    @CsvSource({
            "0, 'Сутки — 400 рублей. Оплата курьеру — наличными или картой.'",
            "1, 'Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.'",
            "2, 'Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.'",
            "3, 'Только начиная с завтрашнего дня. Но скоро станем расторопнее.'",
            "4, 'Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.'",
            "5, 'Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.'",
            "6, 'Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.'",
            "7, 'Да, обязательно. Всем самокатов! И Москве, и Московской области.'"
    })
    public void checkAccordionItem(int index, String text){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickAccordeonButton(index);
        String actual = mainPage.getAccordeonText(index);
        assertEquals(text, actual);
    }

    @Test
    public void goToMainPageTest(){
        MainPage mainPage = new MainPage(driver);

        mainPage.clickTopOrderButton();
        mainPage.clickScooterLogo();
        assertTrue(mainPage.isMainPageDisplayed());
    }

    @Test
    public void goToYandexMainPage() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickYandexLogo();
        Set<String> windowHandles = driver.getWindowHandles();

        String newTabHandle = (String) windowHandles.toArray()[1];
        driver.switchTo().window(newTabHandle);
        Thread.sleep(5000);
        assertTrue(driver.getCurrentUrl().contains("ya.ru"), "url по ссылке: " + driver.getCurrentUrl() + " Должно быть ya.ru");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
