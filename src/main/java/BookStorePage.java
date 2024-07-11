import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {
	private final SelenideElement bookTitle = $("a.book-block-title");
	private final SelenideElement bookPrice = $("span.detail-page-price");
	public BookStorePage bookTitle() {
		bookTitle.click();
		return this;
	}
	public String getBookPrice() {
		return bookPrice.text();
	}


}
