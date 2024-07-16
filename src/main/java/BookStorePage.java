import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class BookStorePage {
    private final SelenideElement bookTitle = $("a.book-block-title");
    private final SelenideElement bookPrice = $("span.detail-page-price");

    // ToDo переделать все геттеры в формат геттера - getBookTitle
    public BookStorePage bookTitle() {
        bookTitle.click();
        return this;
    }

    public String getBookPrice() {
        return bookPrice.text();
    }
}
