package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.Assert;
import static pomAndData.DropdownListPanelText.*;
import pomAndData.HomePage;

public class QuestionsPanelTextTest {

    private WebDriver driver;
    private HomePage objHomePage; // +@Before что бы не создавать в каждом @Test новую переменную-ссылку

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        // driver = new FirefoxDriver();
        driver.manage().window().maximize(); // полное открытие окна, что бы не было перекрытия png самоката
        driver.get("https://qa-scooter.praktikum-services.ru");

        objHomePage = new HomePage(driver);
        objHomePage.scrollQuestions();
        objHomePage.clickCookie(); // иначе кнопка принятия куки перекрывает
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion1() {
        objHomePage.clickAccordionItemHeading1();
        Assert.assertEquals
                ("Неверный текст в 1 вопросе", PANEL_TEXT_1, objHomePage.checkTextAccordionItemPanel1());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion2() {
        objHomePage.clickAccordionItemHeading2();
        Assert.assertEquals
                ("Неверный текст во 2 вопросе", PANEL_TEXT_2, objHomePage.checkTextAccordionItemPanel2());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion3() {
        objHomePage.clickAccordionItemHeading3();
        Assert.assertEquals
                ("Неверный текст в 3 вопросе", PANEL_TEXT_3, objHomePage.checkTextAccordionItemPanel3());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion4() {
        objHomePage.clickAccordionItemHeading4();
        Assert.assertEquals
                ("Неверный текст в 4 вопросе", PANEL_TEXT_4, objHomePage.checkTextAccordionItemPanel4());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion5() {
        objHomePage.clickAccordionItemHeading5();
        Assert.assertEquals
                ("Неверный текст в 5 вопросе", PANEL_TEXT_5, objHomePage.checkTextAccordionItemPanel5());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion6() {
        objHomePage.clickAccordionItemHeading6();
        Assert.assertEquals
                ("Неверный текст в 6 вопросе", PANEL_TEXT_6, objHomePage.checkTextAccordionItemPanel6());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion7() {
        objHomePage.clickAccordionItemHeading7();
        Assert.assertEquals
                ("Неверный текст в 7 вопросе", PANEL_TEXT_7, objHomePage.checkTextAccordionItemPanel7());
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion8() {
        objHomePage.clickAccordionItemHeading8();
            Assert.assertEquals
                    ("Неверный текст в 8 вопросе", PANEL_TEXT_8, objHomePage.checkTextAccordionItemPanel8());

    }

    @After
    public void offBrowser() {
            driver.quit();
    }

}