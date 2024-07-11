import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class MainPage {
	private final SelenideElement search = $("input#query");
	private final SelenideElement bookStore = $("li#bk_store");
	public void search(String query) {
		search.sendKeys(query);
		search.pressEnter();
	}
	public void bookStore() {
		bookStore.click();
	}
}
