import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

	private static final String BASE_URL = "https://www.bkconnection.com/";
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
