import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import jdk.jfr.Description;
import models.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookShopTests extends BaseTest {
    final static String BOOK_TITLE = "Eat That Frog";


    @BeforeEach
    public void beforeEach() {
        open(properties.getProperty("baseUrl"));
        mainPage.declineCookie();
    }

    @Test
    @Description("Проверка корректности работы поиска. Происходит сравнение строки запроса и заголовка первой книги")
    public void firstBookTitleTest() {
        mainPage.search(BOOK_TITLE);
        String onPageBookTitle = searchPage.bookTitle.text();
        assertThat
            (onPageBookTitle.contains(BOOK_TITLE)).isTrue();
    }

    @Test
    @Description("Тест сравнивает цену на странице товара и цену отображаующуюся в корзине, после добавления книги в корзину")
    public void bookPageAndCartPricesComparisonTest() {
        mainPage.goToBookStore();
        String bookPrice = bookStorePage
            .bookTitleClick()
            .getBookPrice();
        bookStorePage.addToCart();
        String cartSubTotalPrice = cartPage
            .checkOut()
            .getCartSubTotalPrice();
        assertThat
            (bookPrice).isEqualTo(cartSubTotalPrice);
    }

    @Test
    @Description("Тест формирование заказа")
    public void orderTest() {
        Card creditCard = Card.getDefaultCard();
        mainPage.login(userName, passWord)
            .goToBookStore();
        bookStorePage.bookTitleClick()
            .addToCart();
        cartPage
            .checkOut()
            .chooseBookFormat("PDF eBook")
            .continueCheckout()
            .continueCheckout()
            .fillCreditCardForm(creditCard);
        assertThat
            (cartPage.getErrorText().contains("attempting to charge")).as(
            "Сайт пропустил несуществующую карту").isTrue();
    }
}
