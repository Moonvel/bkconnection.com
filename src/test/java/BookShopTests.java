import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

import com.codeborne.selenide.ElementsCollection;
import jdk.jfr.Description;
import models.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class BookShopTests extends BaseTest {
    final static String BOOK_TITLE = "Eat That Frog";


    @BeforeEach
    public void beforeEach() {
        open(properties.getProperty("baseUrl"));
        mainPage.declineCookie();
    }

    @Test
    @Tag("smoke")
    @Description("Тест авторизации")
    public void authorizationTest() {
        mainPage.login(userName, passWord);
        Assertions.assertThat
            (mainPage.memberOptionsButton().getText().contains("Member Options")).isTrue();
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
        bookStorePage.addToCartOnBookTitlePage();
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
            .addToCartOnBookTitlePage();
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

    @Test
    @Description("Добавление нескольких книг одного названия, проверка корректности добавления книг по общей стоимости корзины")
    public void orderingSeveralIdenticalBooksTest() {
        int itemsQuantity = 3;
        mainPage.goToBookStore();
        double bookPrice = Double.parseDouble(bookStorePage
            .addToCartOnBookStorePage()
            .continueShopping()
            .bookTitleClick()
            .getBookPrice());
        mainPage
            .cartButtonClick()
            .setItemsQuantity(itemsQuantity);
        double cartSubTotalPrice = Double.parseDouble(cartPage.getCartSubTotalPrice());
        Assertions.assertThat
            (bookPrice * itemsQuantity).isCloseTo(cartSubTotalPrice, Assertions.offset(0.001d));
        System.out.println();
    }

    @Test
    @Description("Добавление указанного количества разных книг, сравнение ожидаемого количества книг и реального количества книг в корзине")
    public void orderingSeveralDifferentBooksTest() {
        long expectedBooksCount = 4;
        mainPage.goToBookStore();
        ElementsCollection titles = bookStorePage.getAddToCartElements();
        titles.stream()
            .limit(expectedBooksCount)
            .forEach(element -> {
                element.click();
                cartPage.continueShopping();
            });
        long booksCount = mainPage
            .cartButtonClick()
            .bookCoverArts().stream().count();
        Assertions.assertThat(expectedBooksCount).isEqualTo(booksCount);
    }
}
