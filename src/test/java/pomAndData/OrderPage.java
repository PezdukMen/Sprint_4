package pomAndData;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {

    // объявление драйвера
    private WebDriver driver;

    // Локатор поля - "Имя"
    private By name = By.xpath(".//input[@placeholder='* Имя']");
    // Локатор поля - "Фамилия"
    private By surname = By.xpath(".//input[@placeholder='* Фамилия']");
    // Локатор поля - "Адрес"
    private By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Локатор поля с выпадающем списком - "Станция метро"
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    // Локатор поля - Телефон
    private By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Локаторы заполняемое поле с выпадающим календарем - "Когда привезти самокат"
    private By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Локатор поля аренды + Локатор выпадающего списка - "Срок аренды"
    private By rentalPeriod = By.cssSelector(".Dropdown-control");
    // Чекбокс страница2 -без локатора, только метод
    // Локаторы поля - Комментарий
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");


    // Локаторы кнопок подтверждения заполнения данных - "Далее" (стр1), "Заказать" (стр2), "Да" (стр3)
    private By onward = By.xpath(".//button[text()='Далее']");
    private By order = By.xpath(".//button[text()='Заказать']");
    private By orderConfirmation = // By.xpath(".//button[text()='Да']"); - НЕ РАБОТАЕТ FF
            By.className("Button_Button__ra12g"); // РАБОТАЕТ FF

    // Конструктор класс
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы заполнения полей стр1
    public void sendKeysName(String nameText) {
        driver.findElement(name).sendKeys(nameText);
    }
    public void sendKeysSurname(String surnameText) {
        driver.findElement(surname).sendKeys(surnameText);
    }
    public void sendKeysAddress(String addressText) {
        driver.findElement(address).sendKeys(addressText);
    }
    public void sendKeysMetroStation(String metroStationText) {
        driver.findElement(metroStation).sendKeys(metroStationText);
        driver.findElement(By.xpath(".//*[text()='" + metroStationText + "']")).click();
    }
    public void sendKeysTelephone(String telephoneNumbers) {
        driver.findElement(telephone).sendKeys(telephoneNumbers);
    }

    //Методы заполнения полей стр2
    public void sendKeysDate(String dateText) {
        driver.findElement(date).sendKeys(dateText);
    }
    public void choiceRentalPeriod(String daysPeriod) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath("//div[contains(text(), '" + daysPeriod + "')]")).click();
    }
    // Метод выбора чекбокса стр2
    public void choiceColor(String color) {
        driver.findElement(By.xpath(".//label[text()='"+color+"']")).click();
    }
    public void sendKeysComment(String commentText) {
        driver.findElement(comment).sendKeys(commentText);
    }

    // Методы нажатия кнопок подтверждения заполнения данных - "Далее" (стр1), "Заказать" (стр2), "Да" (стр3)
    public void clickOnward() {
        driver.findElement(onward).click();
    }
    public void clickOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(order)).click();
        // driver.findElement(order).click();
    }
    public void clickOrderConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(orderConfirmation)).click();
    }

    // Метод проверки появления модалки
    public void checkOrderModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Lvl1: ищем заголовок "Заказ оформлен" (но он #text)
        By headerLocator = By.xpath("//*[contains(., 'Заказ оформлен')]");
                // By.className("Order_ModalHeader__3FDAj");
                // By.xpath("//div[contains(@class, 'Order_ModalHeader')]");
                // By.xpath("/html/body/div/div/div[2]/div[5]/div[1][text()='Заказ оформлен']");
                // By.xpath("//*[contains(., 'Заказ оформлен')]"); - НИЧЕГО не работает на FF, так что ставлю самые красивые
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator));
            System.out.println("Найден заголовок 'Заказ оформлен' в модалке = ОК");
            return; // если нашли = сразу выходим
        } catch (TimeoutException e) {
            System.out.println("Заголовок 'Заказ оформлен' в модалке не найден за 20 секунд, ищем текст...");
        }

        // Lvl2: ищем текст "Номер заказа: "
        By textLocator = By.xpath("//*[contains(., 'Номер заказа: ')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
            System.out.println("Найден текст 'Номер заказа: ' в модалке = ОК");
            return; // если нашли = сразу выходим
        } catch (TimeoutException e) {
            System.out.println("Текст 'Номер заказа: ' в модалке не найден за 20 секунд, ищем кнопку...");
        }

        // lvl3: ищем кнопку "Посмотреть статус"
        By buttonLocator =
                By.xpath("//*[contains(., 'Посмотреть статус')]");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLocator));
            System.out.println("Найдена кнопка 'Посмотреть статус' в модалке = ОК");
            return; // нашли кнопку = сразу выходим
        } catch (TimeoutException e) {
            Assert.fail("Модалка не появилась: ни заголовка 'Заказ оформлен', " +
                    "ни текста 'Номер заказа: ', " +
                    "ни кнопки 'Посмотреть статус'");
        }
    }

}
