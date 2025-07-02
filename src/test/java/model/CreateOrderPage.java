package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateOrderPage {

    private WebDriver driver;

    //Страница создания заказа
    private By createOrderPage = By.xpath(".//div[text()='Для кого самокат']");
    //Поле ввода имени
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private By adressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Выбор станции из списка
    private String setMetroStation = ".//div[text()='%s']";

    //Поле ввода телефона
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка куки
    private By cookieAcceptBitton = By.xpath(".//button[text()='да все привыкли']");
    //Кнопка далее
    private By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Поле ввода даты
    private By deliveryDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выбор текущей даты
    private By todaysDateDatepicker = By.xpath(".//div[contains(@class,'today')]");
    //Поле выбора срока аренды
    private By rentalPeriodField = By.xpath(".//span[@class='Dropdown-arrow']");
    //Выбор периода в выпадающем списке
    private By twoDaysPeriod = By.xpath(".//div[@class='Dropdown-option'][1]");
    //Чекбокс выбора цвета
    private String colorCheckbox = ".//label[@for='%s']";
    //Поле ввода комментария
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Кнопка подтверждения заказа
    private By confirmButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    //Окно с подтверждением заказа
    private By orderSuccess = By.xpath(".//div[text()='Заказ оформлен']");

    //Сообщение об ошибке
    private String errorMessage = ".//div[contains(@class,'Input_ErrorMessage') and text()='%s']";

    public CreateOrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillFirstOrderPage(String firstName, String surname, String address,
                                   String metroStation, String phoneNumber){
        driver.findElement(cookieAcceptBitton).click();
        driver.findElement(nameField).sendKeys(firstName);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(adressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath(String.format(setMetroStation, metroStation))).click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
        driver.findElement(nextButton).click();
    }

    public void fillSecondOrderPage(String scooterColor, String comment){
        driver.findElement(deliveryDateField).click();
        driver.findElement(todaysDateDatepicker).click();
        driver.findElement(deliveryDateField).click();

        driver.findElement(rentalPeriodField).click();
        driver.findElement(twoDaysPeriod).click();
        driver.findElement(By.xpath(String.format(colorCheckbox, scooterColor))).click();
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
        driver.findElement(confirmButton).click();
    }

    public boolean isOrderPageDisplayed(){
        return driver.findElement(createOrderPage).isDisplayed();
    }


    public boolean isOrderSuccessDisplayed(){
        return driver.findElement(orderSuccess).isDisplayed();
    }

    public boolean isErrorMessageDisplayed(String errorField){
        return driver.findElement(By.xpath(String.format(errorMessage, errorField))).isDisplayed();
    }


}
