import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CartPage {
    private final SelenideElement checkOut = $x("//a[normalize-space(text())='Checkout']");
    private final SelenideElement addToCart = $("button.cart-btn");
    private final SelenideElement productTotalPrice = $("div.summary-table-overlay td");
    private final SelenideElement continueCheckout = $("*.btn-primary.continue");
    private final SelenideElement creditCardNumber = $("input#order_credit_card_number");
    private final SelenideElement creditCardMonthExpire = $("input#order_cc_expiration_month");
    private final SelenideElement creditCardYearExpire = $("input#order_cc_expiration_year");
    private final SelenideElement cvvCode = $("input#order_cvv");
    private final SelenideElement secureCheckout = $("input.btn-warning");
    private final SelenideElement errorText = $("p.error-text");


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

    public CartPage continueCheckout() {
        continueCheckout.click();
        return this;
    }

    /*
    ToDo лучше разбить на отдельные методы (не делаем, если уверены, что АБСОЛЮТНО ТОЧНО будут не нужны)
    в данном методе вызываем ранее созданные
    в параметрах метода ждем Card class со всеми данными
    параметры пишем или все в строку или каждый с новой строки
     */
    public void fillCreditCardForm(String cardNumber, String cardMonthExpire,
                                   String cardYearExpire, String cvv) {
        creditCardNumber.sendKeys(cardNumber);
        creditCardMonthExpire.sendKeys(cardMonthExpire);
        creditCardYearExpire.sendKeys(cardYearExpire);
        cvvCode.sendKeys(cvv);
        secureCheckout.click();
    }

    public String getErrorText() {
        errorText.shouldBe(visible);
        return errorText.text();
    }
}
