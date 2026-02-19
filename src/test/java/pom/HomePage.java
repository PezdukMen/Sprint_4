package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    // объявление драйвера
    private WebDriver driver;

    // Локатор заголовка (но он <div>) "Вопросы о важном"
    private By questions = By.className("Home_SubHeader__zwi_E");

    // Локаторы выпадающего списка - Заголовок
    private By accordionItemHeading = By.xpath(".//div[@class='accordion__panel']/p");

    //Локатор кнопки "Да все привыкли" - принятие куки
    private By cookie = By.id("rcc-confirm-button");
    //Локатор кнопки "Заказать" - вверху
    private By buttonUp = By.cssSelector("button.Button_Button__ra12g:not(.Button_Middle__1CSJM)");
    //Локатор кнопки "Заказать" - внизу
    private By buttonDown = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM");

    // Конструктор класс
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод скролл до заголовка (но он <div>) "Вопросы о важном"
    public void scrollQuestions() {
        WebElement element = driver.findElement(questions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Метод принятие куки
    public void clickCookie() {
        driver.findElement(cookie).click();
    }

    // Метод клика по аккордеону и получения текста из открывающейся панели + проверка текста
    public void clickQuestionAndCheckText(int index, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Клик по аккордеону (индекс с 0)
        String questionXpath = "(//div[contains(@id, 'accordion__heading')])[" + (index + 1) + "]";
        driver.findElement(By.xpath(questionXpath)).click();

        // Ждем появления текста с панели
        String panelId = "accordion__panel-" + index;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));

        // Берем текст и проверяем
        String actualText = driver.findElement(By.id(panelId)).getText().trim();
        Assert.assertEquals("Неверный текст в вопросе №" + index, expectedText, actualText);
    }

    // Методы клика на кнопки "Заказать" (вверху/внизу)
    public void clickButtonUp() {
        driver.findElement(buttonUp).click();
    }
    public void clickButtonDown() { // ожидание кликабельности > скролл > клик
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(buttonDown));

        WebElement element = driver.findElement(buttonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(buttonDown).click();
    }

}
