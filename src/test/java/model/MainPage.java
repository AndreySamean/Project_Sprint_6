package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //Кнопка заказа в заголовке
    private By topOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");
    //Кнопка заказа внизу страницы
    private By bottomOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //FAQ "Вопросы о важном"
    private String accordionButton = ".//div[@id='accordion__heading-%d']";
    private String  accordionText = ".//div[@id='accordion__panel-%d']";

    //Логотип самоката
    private By scooterLogo = By.xpath(".//img[@alt='Scooter']");
    //Текст главной страницы
    private By mainPageHeaderText = By.xpath(".//div[@class='Home_Header__iJKdX']");
    //Логотип Яндекс
    private By yandexLogoHeader = By.xpath(".//img[@alt='Yandex']");

    //Кнопка Go!
    private By goButton = By.xpath(".//button[@class='Button_Button__ra12g Header_Button__28dPO']");
    //Поле ввода номера заказа
    private By enterOrderField = By.xpath(".//input[@placeholder='Введите номер заказа']");
    //Кнопка статус заказа
    private By orderStatusButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    //Изображение "Такого заказа нет"
    private By notFoundImage = By.xpath(".//img[@alt='Not found']");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickTopOrderButton(){
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton(){
        WebElement button = driver.findElement(bottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void clickAccordeonButton(int index){
        WebElement button = driver.findElement(By.xpath(String.format(accordionButton, index)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(button));
        button.click();

    }

    public String getAccordeonText(int index){
        WebElement text = driver.findElement(By.xpath(String.format(String.valueOf(accordionText), index)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", text);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(text));
        return text.getText();
    }

    public void clickScooterLogo(){
        driver.findElement(scooterLogo).click();
    }

    public boolean isMainPageDisplayed(){
        return driver.findElement(mainPageHeaderText).isDisplayed();
    }

    public void clickYandexLogo(){
        driver.findElement(yandexLogoHeader).click();
    }

    public void checkOrderStatus(String orderNumber){
        driver.findElement(orderStatusButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterOrderField));
        driver.findElement(enterOrderField).sendKeys(orderNumber);
        driver.findElement(goButton).click();
    }

    public boolean isNotFoundImageDisplayed(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
        return driver.findElement(notFoundImage).isDisplayed();
    }
}
