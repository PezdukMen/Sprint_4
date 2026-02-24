package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.CoreMatchers.containsString;
import pom.HomePage;
import pom.OrderPage;

@RunWith(Parameterized.class)
public class OrderAScooterTest {


    private WebDriver driver;
    private HomePage objHomePage; // +@Before что бы не создавать в каждом @Test новую переменную-ссылку
    private OrderPage objOrderPage;

    // Кнопки "Заказать" = true (верхняя)
    //                   = false (нижняя)
    private boolean clickButtonUp;
    // стр1
    private final String nameUser;
    private final String surnameUser;
    private final String addressUser;
    private final String metroStationUser;
    private final String telephoneNumbersUser;
    // стр2
    private final String dateUser;
    private final String daysPeriodUser;
    private final String colorUser;
    private final String commentUser;

    public OrderAScooterTest
            (boolean clickButtonUp,
             String nameText, String surnameText, String addressText, String metroStationText, String telephoneNumbers,
             String dateText, String daysPeriodUser, String color, String commentText) {
        this.clickButtonUp = clickButtonUp;
        this.nameUser = nameText;
        this.surnameUser = surnameText;
        this.addressUser = addressText;
        this.metroStationUser = metroStationText;
        this.telephoneNumbersUser = telephoneNumbers;
        this.dateUser = dateText;
        this.daysPeriodUser = daysPeriodUser;
        this.colorUser = color;
        this.commentUser = commentText;
    }

    @Parameterized.Parameters
    public static Object[][] getFullOrder() {
        return new Object[][]{
                {true,
                        "Игорь", "Иванович", "Советская, 76, 198", "Черкизовская", "89888888881",
                        "10.10.2026", "сутки", "чёрный жемчуг", "можно не привозить"},
                {false,
                        "Наталья", "Попова", "Тверская дом 455", "Сокольники", "952335489655",
                        "29/06/2026", "четверо суток", "серая безысходность", "обязательно необходимо привезти"},
        };
    }

    @Before
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // полное открытие окна, что бы не было перекрытия png самоката
        driver.get("https://qa-scooter.praktikum-services.ru");

        objHomePage = new HomePage(driver);
        objHomePage.clickCookie(); // иначе кнопка принятия куки перекрывает
    }

    @Test
    public void shouldOrderScooter() {
        // Выбор кнопки "Заказать"
        if (clickButtonUp) {
            objHomePage.clickButtonUp();
        } else {
            objHomePage.clickButtonDown();
        }

        objOrderPage = new OrderPage(driver);
        // стр1
        objOrderPage.sendKeysName(nameUser);
        objOrderPage.sendKeysSurname(surnameUser);
        objOrderPage.sendKeysAddress(addressUser);
        objOrderPage.sendKeysMetroStation(metroStationUser);
        objOrderPage.sendKeysTelephone(telephoneNumbersUser);
        objOrderPage.clickOnward();
        // стр2
        objOrderPage.choiceRentalPeriod(daysPeriodUser); // иначе календарь перекрывает
        objOrderPage.sendKeysDate(dateUser);
        objOrderPage.choiceColor(colorUser);
        objOrderPage.sendKeysComment(commentUser);
        objOrderPage.clickOrder();
        objOrderPage.clickOrderConfirmation();
        // Проверка появления модалки через текст
        String expectedSuccessModalHeader = "Заказ оформлен";
        MatcherAssert.assertThat(
                "Модалка успеха не появилась за 10 секунд",
                objOrderPage.getSuccessModalHeader(),
                containsString(expectedSuccessModalHeader));
    }

    @After
    public void offBrowser() {
        driver.quit();
    }

}