import static com.codeborne.selenide.Selenide.closeWebDriver;

import com.codeborne.selenide.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pageObject.BookStorePage;
import pageObject.CartPage;
import pageObject.MainPage;
import pageObject.SearchPage;

public class BaseTest {
    protected static String userName;
    protected static String passWord;
    static MainPage mainPage;
    static SearchPage searchPage;
    static BookStorePage bookStorePage;
    static CartPage cartPage;
    static Properties properties;


    @BeforeAll
    public static void setUp() throws IOException {
        mainPage = new MainPage();
        searchPage = new SearchPage();
        bookStorePage = new BookStorePage();
        cartPage = new CartPage();

        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";

        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/config.properties"));
        userName = properties.getProperty("userName");
        passWord = properties.getProperty("password");
    }

    @AfterEach
    public void afterEach() {
        closeWebDriver();
    }
}
