package pageObject;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.SelenideElement;
import models.Card;

public class CartPage {
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


    public CartPage checkOut() {
        $("body.modal-open").should(exist).shouldBe(visible);
        if (checkOut.exists()) {
            executeJavaScript("arguments[0].click();", checkOut);
        }
        return this;
    }

    public String getCartSubTotalPrice() {
        return productSubTotalPrice.text();
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
    public void fillCreditCardForm(Card card) {
        creditCardNumber.sendKeys(card.getCardNumber());
        creditCardMonthExpire.sendKeys(card.getCardMonthExpire());
        creditCardYearExpire.sendKeys(card.getCardYearExpire());
        cvvCode.sendKeys(card.getCvv());
        secureCheckout.click();
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
}
