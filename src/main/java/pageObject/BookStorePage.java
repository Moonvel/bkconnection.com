package pageObject;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {
    private final SelenideElement bookTitle = $("a.book-block-title");
    private final SelenideElement bookPrice = $("span.detail-page-price");
    private final SelenideElement addToCart = $("div.detail-page-cart-button button.cart-btn");


    public BookStorePage bookTitleClick() {
        bookTitle.click();
        return this;
    }

    public String getBookPrice() {
        return bookPrice.text();
    }
    public void addToCart() {
        addToCart.click();
    }
}