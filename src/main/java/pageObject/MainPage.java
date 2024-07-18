package pageObject;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class MainPage {
	private final SelenideElement search = $("input#query");
	private final SelenideElement bookStore = $("li#bk_store");
	private final SelenideElement cookie = $("a#hs-eu-decline-button");
	private final SelenideElement loginButton = $x("//a[normalize-space(text())='Login']");
	private final SelenideElement memberOptionsButton = $x("//a[normalize-space(text())='Member Options']");
	private final SelenideElement loginInput = $("input#refinery_user_login");
	private final SelenideElement passwordInput = $("input#refinery_user_password");
	private final SelenideElement signIn = $("input[value='Sign In']");


	public void search(String query) {
		search.sendKeys(query);
		search.pressEnter();
	}

	public void goToBookStore() {
		bookStore.click();
	}

	public void declineCookie() {
			cookie.shouldBe(visible, Duration.ofSeconds(10)).click();
	}

	public MainPage login(String login, String password) {
		loginButton.click();
		loginInput.sendKeys(login);
		passwordInput.sendKeys(password);
		signIn.click();
		return this;
	}

	public SelenideElement memberOptionsButton() {
		return memberOptionsButton;
	}
}
