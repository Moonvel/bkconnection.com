package pageObject;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import models.Card;
import org.openqa.selenium.Keys;

public class CartPage {
    MainPage mainPage = new MainPage();
    private final SelenideElement checkOut = $x("//a[normalize-space(text())='Checkout']");
    private final SelenideElement productSubTotalPrice = $("div.summary-table-overlay td");
    private final SelenideElement continueCheckout = $("*.btn-primary.continue");
    private final SelenideElement creditCardNumber = $("input#order_credit_card_number");
    private final SelenideElement creditCardMonthExpire = $("input#order_cc_expiration_month");
    private final SelenideElement creditCardYearExpire = $("input#order_cc_expiration_year");
    private final SelenideElement cvvCode = $("input#order_cvv");
    private final SelenideElement secureCheckout = $("input.btn-warning");
    private final SelenideElement errorText = $("p.error-text");
    private final SelenideElement bookFormatDropDown = $("select#line_item_product_id");
    private final SelenideElement successfulPurchaseMessage = $x("//h1[contains(text(), 'Thanks')]");
    private final SelenideElement productAddedWindow = $("body.modal-open");
    private final SelenideElement itemsQuantity = $("input[name='line_item[qty]']");
    private final SelenideElement continueShopping = $x("//a[text()='Continue Shopping']");
    private final ElementsCollection bookCoverArts = $$("img.book-coverart");


    public CartPage checkOut() {
        try {
            productAddedWindow.shouldBe(visible);
            executeJavaScript("arguments[0].click();", checkOut);
        } catch (ElementNotFound e) {
            System.out.println("Окно после добавления товара, не появилось");
        }
        return this;
    }

    public CartPage continueShopping() {
        try {
            productAddedWindow.shouldBe(visible);
            executeJavaScript("arguments[0].click();", continueShopping);
        } catch (ElementNotFound e) {
            Selenide.back();
            System.out.println("Окно после добавления товара, не появилось, производится переход на предыдущую страницу");
        }
        return this;
    }

    public String getCartSubTotalPrice() {
        return productSubTotalPrice.text().trim().substring(1);
    }

    public CartPage continueCheckout() {
        continueCheckout.click();
        return this;
    }

    public void fillCreditCardNumber(Card card) {
        creditCardNumber.sendKeys(card.getCardNumber());
    }

    public void fillCreditCardMonthExpire(Card card) {
        creditCardMonthExpire.sendKeys(card.getCardMonthExpire());
    }

    public void fillCreditCardYearExpire(Card card) {
        creditCardYearExpire.sendKeys(card.getCardYearExpire());
    }

    public void fillCvvCode(Card card) {
        cvvCode.sendKeys(card.getCvv());
    }

    public void secureCheckoutClick() {
        secureCheckout.click();
    }

    public void fillCreditCardForm(Card card) {
        fillCreditCardNumber(card);
        fillCreditCardMonthExpire(card);
        fillCreditCardYearExpire(card);
        fillCvvCode(card);
        secureCheckoutClick();
    }

    public String getErrorText() {
        errorText.shouldBe(visible);
        return errorText.text();
    }

    public String getSuccessfulPurchaseMessage() {
        successfulPurchaseMessage.shouldBe(visible);
        return successfulPurchaseMessage.text();
    }

    public CartPage chooseBookFormat(String format) {
        bookFormatDropDown.selectOptionContainingText(format);
        return this;
    }
    public CartPage setItemsQuantity(int quantity) {
        itemsQuantity.sendKeys(Keys.BACK_SPACE, String.valueOf(quantity));
        itemsQuantity.pressEnter();
        return this;
    }

    public ElementsCollection getBookCoverArts() {
        return bookCoverArts;
    }


}
