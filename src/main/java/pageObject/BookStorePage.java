package pageObject;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class BookStorePage {
    private final SelenideElement bookTitle = $("a.book-block-title");
    private final SelenideElement bookPrice = $("span.detail-page-price");
    private final SelenideElement addToCartOnBookTitlePage = $("div.detail-page-cart-button button.cart-btn");
    public final SelenideElement addToCartOnBookStorePage = $("div.book-button-for-desktop.col-md-3 button");
    private final ElementsCollection  addToCartElements = $$("div.book-button-for-desktop.col-md-3 button");
    private final SelenideElement continueShopping = $x("//a[text()='Continue Shopping']");


    public BookStorePage bookTitleClick() {
        bookTitle.click();
        return this;
    }

    public String getBookPrice() {
        return bookPrice.text().substring(1);
    }

    public void addToCartOnBookTitlePage() {
        addToCartOnBookTitlePage.click();
    }

    public BookStorePage addToCartOnBookStorePage() {
        addToCartOnBookStorePage.click();
        return this;
    }

    public BookStorePage continueShopping() {
        continueShopping.click();
        return this;
    }

    public ElementsCollection getAddToCartElements() {
        return addToCartElements;
    }

}
