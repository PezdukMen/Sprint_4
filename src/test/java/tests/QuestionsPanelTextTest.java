package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import pom.HomePage;

@RunWith(Parameterized.class)
public class QuestionsPanelTextTest {

    private WebDriver driver;
    private HomePage objHomePage; // +@Before что бы не создавать в каждом @Test новую переменную-ссылку

    private final int index;
    private final String expectedText;

    public QuestionsPanelTextTest(int index, String expectedText) {
        this.index = index;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getFullAccordionPanelText() {
        return new Object[][]{
                {0,
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1,
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                                "можете просто сделать несколько заказов — один за другим."},
                {2,
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                                "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                                "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3,
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4,
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                                "по красивому номеру 1010."},
                {5,
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже " +
                                "если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6,
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                                "Все же свои."},
                {7,
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // полное открытие окна, что бы не было перекрытия png самоката
        driver.get("https://qa-scooter.praktikum-services.ru");

        objHomePage = new HomePage(driver);
        objHomePage.scrollQuestions();
        objHomePage.clickCookie(); // иначе кнопка принятия куки перекрывает
    }

    @Test
    public void shouldDisplayCorrectTextForFAQQuestion() {
        String actualText = objHomePage.getAccordionPanelText(index);
        Assert.assertEquals("Неверный текст в вопросе №" + index, expectedText, actualText);
    }

    @After
    public void offBrowser() {
            driver.quit();
    }

}