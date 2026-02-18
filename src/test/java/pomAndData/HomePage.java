/* package pomAndData;

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
    private By accordionItemHeading1 = By.xpath(".//*[@class='accordion__item'][1]");
    private By accordionItemHeading2 = By.xpath(".//*[@class='accordion__item'][2]");
    private By accordionItemHeading3 = By.xpath(".//*[@class='accordion__item'][3]");
    private By accordionItemHeading4 = By.xpath(".//*[@class='accordion__item'][4]");
    private By accordionItemHeading5 = By.xpath(".//*[@class='accordion__item'][5]");
    private By accordionItemHeading6 = By.xpath(".//*[@class='accordion__item'][6]");
    private By accordionItemHeading7 = By.xpath(".//*[@class='accordion__item'][7]");
    private By accordionItemHeading8 = By.xpath(".//*[@class='accordion__item'][8]");
    // Локаторы выпадающего списка - Раскрывающаяся панель с текстом
    private By accordionItemPanel1 = By.cssSelector("#accordion__panel-0");
    private By accordionItemPanel2 = By.cssSelector("#accordion__panel-1");
    private By accordionItemPanel3 = By.cssSelector("#accordion__panel-2");
    private By accordionItemPanel4 = By.cssSelector("#accordion__panel-3");
    private By accordionItemPanel5 = By.cssSelector("#accordion__panel-4");
    private By accordionItemPanel6 = By.cssSelector("#accordion__panel-5");
    private By accordionItemPanel7 = By.cssSelector("#accordion__panel-6");
    private By accordionItemPanel8 = By.cssSelector("#accordion__panel-7");

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
    // Метод скролл до кнопки "Заказать" (внизу)
    public void scrollButtonDown() { // ожидание, потому что просто скролл иногда падает как минимум в FF
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(buttonDown));

        WebElement element = driver.findElement(buttonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Метод принятие куки
    public void clickCookie() {
        driver.findElement(cookie).click();
    }

    // Методы клика по заголовку
    public void clickAccordionItemHeading1() {
        driver.findElement(accordionItemHeading1).click();
    }
    public void clickAccordionItemHeading2() {
        driver.findElement(accordionItemHeading2).click();
    }
    public void clickAccordionItemHeading3() {
        driver.findElement(accordionItemHeading3).click();
    }
    public void clickAccordionItemHeading4() {
        driver.findElement(accordionItemHeading4).click();
    }
    public void clickAccordionItemHeading5() {
        driver.findElement(accordionItemHeading5).click();
    }
    public void clickAccordionItemHeading6() {
        driver.findElement(accordionItemHeading6).click();
    }
    public void clickAccordionItemHeading7() {
        driver.findElement(accordionItemHeading7).click();
    }
    public void clickAccordionItemHeading8() {
        driver.findElement(accordionItemHeading8).click();
    }

    // Методы получения текста раскрывающейся панели
    public String checkTextAccordionItemPanel1() {
        return driver.findElement(accordionItemPanel1).getText();
    }
    public String checkTextAccordionItemPanel2() {
        return driver.findElement(accordionItemPanel2).getText();
    }
    public String checkTextAccordionItemPanel3() {
        return driver.findElement(accordionItemPanel3).getText();
    }
    public String checkTextAccordionItemPanel4() {
        return driver.findElement(accordionItemPanel4).getText();
    }
    public String checkTextAccordionItemPanel5() {
        return driver.findElement(accordionItemPanel5).getText();
    }
    public String checkTextAccordionItemPanel6() {
        return driver.findElement(accordionItemPanel6).getText();
    }
    public String checkTextAccordionItemPanel7() {
        return driver.findElement(accordionItemPanel7).getText();
    }
    public String checkTextAccordionItemPanel8() {
        return driver.findElement(accordionItemPanel8).getText();
    }

    // Методы клика на кнопки "Заказать" (вверху/внизу)
    public void clickButtonUp() {
        driver.findElement(buttonUp).click();
    }
    public void clickButtonDown() {
        driver.findElement(buttonDown).click();
    }

}
