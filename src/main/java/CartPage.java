import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.SelenideElement;

public class CartPage {
	private final SelenideElement checkOut = $x("//a[normalize-space(text())='Checkout']");
	private final SelenideElement addToCart = $("button.cart-btn");
	private final SelenideElement productTotalPrice = $("div.summary-table-overlay td[align]");
	public CartPage addToCart() {
		executeJavaScript("arguments[0].click();", addToCart);
		return this;
	}
	public CartPage checkOut() {
		checkOut.click();
		return this;
	}
	public String getCartTotalPrice() {
		return productTotalPrice.text();
	}
}
