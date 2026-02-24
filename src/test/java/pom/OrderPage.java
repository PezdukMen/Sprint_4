package pom;

import org.openqa.selenium.*;
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
    // Локатор поля с выпадающем списком - "Станция метро" + Выпадающий список
    private By metroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    private By metroStationList = By.cssSelector("div.select-search__select");
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
    // полный путь, т.к. 2 кнопки "Заказать", самое устойчивое
    private By order =
            By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");
    private By orderConfirmation =
            By.xpath("//button[contains(., 'Да') and contains(@class, 'Button_Middle')]");

    // Локатор заголовка <div> модального окна "Заказ оформлен"
    private By successModalHeader =
            By.xpath(".//*[contains(., 'Заказ оформлен') and contains(@class, 'ModalHeader')]");

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
        // Создаем объект, который умеет ждать
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Подготовка поля + ввод значения
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(metroStation));
        input.click();
        input.clear();
        input.sendKeys(metroStationText);
        // Ждем список
        wait.until(ExpectedConditions.visibilityOfElementLocated(metroStationList));
        // Кликаем по нужному тексту ТОЛЬКО ВНУТРИ списка
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'select-search__select')]//*[text()='" + metroStationText + "']")
        )).click();
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
        driver.findElement(By.xpath(".//div[contains(text(), '" + daysPeriod + "')]")).click();
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
        driver.findElement(order).click();
    }
    public void clickOrderConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(orderConfirmation)).click();
    }

    // Метод проверки появления модалки, через ожидание + возврат
    public String getSuccessModalHeader() {
        // Объект ожидания
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Ожидаем "Заказ оформлен"
        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(successModalHeader)
        );
        // Получаем + возвращаем текст; убирая пробелы, переносы строк и табуляции с начала и конца строки
        return header.getText().trim();
    }

}
