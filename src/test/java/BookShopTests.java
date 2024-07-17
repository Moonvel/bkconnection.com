import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class BookShopTests extends BaseTest {
    final static String BOOK_TITLE = "Eat That Frog";

    /* ToDo
    для автоформатирования кода и импортов не забываем после внесения изменений в код использовать CTRL+ALT+L и CTRL+ALT+O
     */
    /*
    ToDo у меня тест повисел и упал - надо смотреть
    тестам давай более осмысленные имена, если чекаешь какой либо один заголовок - первый, так и назови
    если в первых пяти - опять же должно быть отражено
    вроде мелочь, а суть теста становится по коду сразу понятна
     */
    @Test
    @Description("Проверка наличия названия книги в заголовке")
    public void bookTitleTest() {
        mainPage.search(BOOK_TITLE);
        /*
        ToDo тут поиск по тайтлу, смысл его чекать, если по нему и нашли?
        шаг получения текста лучше из ассерта вынести отдельно
         */
        Assertions.assertThat(searchPage.bookTitle.text()).contains(BOOK_TITLE);
    }
    /*
    ToDo не понял название и смысл теста - при чем тут корзина?
     */
    @Test
    @Description("Сравнение стоимости книги на странице товаров с ценой книги в корзине")
    public void cartTest() {
        mainPage.bookStore();
        String bookPrice = bookStorePage.bookTitle()
                .getBookPrice();
        bookStorePage.addToCart();
        String cartTotalPrice = cartPage.checkOut()
                .getCartTotalPrice();
        /* ToDo обычно статик импорт метода делают
        assertThat и саму проверку делают на разных строках
         */
        Assertions.assertThat(bookPrice).
                isEqualTo(cartTotalPrice);
    }

    /*
    ToDo тест тоже упал
     */
    @Test
    @Description("Тест формирование заказа")
    public void orderTest() {
        /*
        ToDo предлагаю факультативом внести данные в файл и начитывать оттуда перед запуском тестов
         */
        mainPage.login("moonvel@mail.ru", "easyPass")
                .bookStore();
        /* ToDo получаем тайтл для чего? если хоти проверить  - проверяем хотя бы линейно, в идеале SoftAssert
        вообще на будущее - один тест, одна смысловая проверка в нем
         */
        bookStorePage.bookTitle();
        /*
        ToDO на будущее - если используем тест данные, то храним их хотя бы в объекте
        1) создаем DTO class с нужной структурой - Card
        2) создаем пакет и класс под нужные тестовые данные, типа abstract CardData
        3) в классе делаем статический метод - getDefaultCard
        4) вызываем нужный метод в начале теста и кладем в объект - сразу видно тестовые данные, используемые в тесте
         */
        bookStorePage.addToCart();
        cartPage.checkOut()
                .continueCheckout()
                .continueCheckout()
                .fillCreditCardForm("4111111111111111",
                        "12", "2025", "123");
        Assertions.assertThat(cartPage.getErrorText()).contains("attempting to charge");
    }
}
