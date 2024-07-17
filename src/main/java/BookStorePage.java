import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {
    private final SelenideElement bookTitle = $("a.book-block-title");
    private final SelenideElement bookPrice = $("span.detail-page-price");
    private final SelenideElement addToCart = $("div.detail-page-cart-button button.cart-btn");
    // ToDo переделать все геттеры в формат геттера - getBookTitle
    public BookStorePage bookTitle() {
        bookTitle.click();
        return this;
    }

    public String getBookPrice() {
        return bookPrice.text();
    }
    public BookStorePage addToCart() {
        addToCart.click();
        return this;
    }
}
