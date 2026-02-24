package pom;

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

    // Метод кликает по вопросу и возвращает текст из раскрывшейся панели
    public String getAccordionPanelText(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Клик по вопросу
        String questionXpath = "(//div[contains(@id, 'accordion__heading')])[" + (index + 1) + "]";
        driver.findElement(By.xpath(questionXpath)).click();

        // Ждем панель
        String panelId = "accordion__panel-" + index;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(panelId)));

        // Возвращаем текст (без проверки)
        return driver.findElement(By.id(panelId)).getText().trim();
    }

    // Методы клика на кнопки "Заказать" (вверху/внизу)
    public void clickButtonUp() {
        driver.findElement(buttonUp).click();
    }
    public void clickButtonDown() { // ожидание кликабельности > скролл > клик
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.elementToBeClickable(buttonDown));

        WebElement element = driver.findElement(buttonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElement(buttonDown).click();
    }

}
