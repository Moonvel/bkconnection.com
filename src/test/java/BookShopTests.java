import jdk.jfr.Description;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookShopTests extends BaseTest {

	final static String BOOK_TITLE = "Eat That Frog";

	@Test
	@Description("Проверка наличия названия книги в заголовке")
	public void bookTitleTest() {
		mainPage.search(BOOK_TITLE);
		Assertions.assertThat(searchPage.bookTitle.text()).contains(BOOK_TITLE);
	}
	@Test
	@Description("Сравнение стоимости книги на странице товаров с ценой книги в корзине")
	public void cartTest() {
		mainPage.bookStore();
		String bookPrice = bookStorePage
				.bookTitle()
				.getBookPrice();
		String cartTotalPrice = cartPage
				.addToCart()
				.checkOut()
				.getCartTotalPrice();
		Assertions.assertThat(bookPrice).isEqualTo(cartTotalPrice);
	}


}
