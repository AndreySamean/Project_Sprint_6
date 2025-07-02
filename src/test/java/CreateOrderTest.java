import model.CreateOrderPage;
import model.MainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateOrderTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = MainPageTest.getWebDriver(Browser.CHROME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public static WebDriver getWebDriver(Browser browser){
        switch (browser){
            case CHROME:  return new ChromeDriver();
            case FIREFOX: return new FirefoxDriver();
            default: throw new RuntimeException("Неизвестный браузер");
        }
    }

    @ParameterizedTest(name = "Проверка кнопки {0}")
    @CsvSource({
            "верхняя",
            "нижняя"
    })
    public void entryPointTest(String entryPoint){

        MainPage mainPage = new MainPage(driver);
        CreateOrderPage orderPage = new CreateOrderPage(driver);

        if ("верхняя".equals(entryPoint)){
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }
        assertTrue(orderPage.isOrderPageDisplayed());
    }

    @ParameterizedTest
    @CsvSource({
            // Формат: Имя, Фамилия, Адрес, Станция метро, Телефон, Цвет, Комментарий, Точка входа
            "Иван, Иванов, Москва ул. Ленина 1, Черкизовская, +79991234567, black, Позвонить за час",
            "Петр, Петров, СПб Невский 100, Сокольники, 89998765432, grey, Не звонить",
            "Анна, Сидорова, Казань Кремлевская 35, Румянцево, +79995554433, black, ''",
            "Елена, Сергеева, Новосибирск Красный проспект 50, Парк культуры, +79991112233, grey, ''"
    })
    public void testOrderFlowTest(
            String firstName, String surname, String address,
            String metroStation, String phoneNumber, String scooterColor,
            String comment) {

        MainPage mainPage = new MainPage(driver);
        CreateOrderPage orderPage = new CreateOrderPage(driver);

        mainPage.clickTopOrderButton();

        orderPage.fillFirstOrderPage(firstName, surname, address, metroStation, phoneNumber);
        orderPage.fillSecondOrderPage(scooterColor, comment);

        assertTrue(orderPage.isOrderSuccessDisplayed());
    }

    @ParameterizedTest(name="Некорректно заполнено поле {5}")
    @CsvSource({
            "1, Иванов, Москва ул. Ленина 1, Черкизовская, +79991234567, Введите корректное имя",
            "Иван, 1, Москва ул. Ленина 1, Черкизовская, +79991234567, Введите корректную фамилию",
            "Иван, Иванов, 1, Черкизовская, +79991234567, Введите корректный адрес",
            "Иван, Иванов, Москва ул. Ленина 1, Черкизовская, 1, Введите корректный номер"
    })
    public void errorInFirstOrderPageIsDisplayed(String firstName, String surname, String address,
                                             String metroStation, String phoneNumber, String errorMessage){
        MainPage mainPage = new MainPage(driver);
        CreateOrderPage orderPage = new CreateOrderPage(driver);

        mainPage.clickTopOrderButton();
        orderPage.fillFirstOrderPage(firstName, surname, address, metroStation, phoneNumber);
        orderPage.isErrorMessageDisplayed(errorMessage);
    }



    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
