package pageObject;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class SearchPage {
	public final SelenideElement bookTitle = $(By.cssSelector("a.book-block-title"));
}
