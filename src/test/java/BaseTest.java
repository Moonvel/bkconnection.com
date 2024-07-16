import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    private static final String BASE_URL = "https://www.bkconnection.com/"; // ToDo также лучше вынести в проперти-файл

    static MainPage mainPage;
    static SearchPage searchPage;
    static BookStorePage bookStorePage;
    static CartPage cartPage;

    @BeforeAll
    public static void setUp() {
        mainPage = new MainPage();
        searchPage = new SearchPage();
        bookStorePage = new BookStorePage();
        cartPage = new CartPage();

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    /*
    ToDo в текущем контексте предлагаю все же перенести в класс с тестами
     */
    @BeforeEach
    public void beforeEach() {
        open(BASE_URL);
        mainPage.declineCookie();
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
