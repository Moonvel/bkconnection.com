package models;

public class Card {
  private final String cardNumber;
  private final String cardMonthExpire;
  private final String cardYearExpire;
  private final String cvv;

  public Card(String cardNumber, String cardMonthExpire, String cardYearExpire,
      String cvv) {
    this.cardNumber = cardNumber;
    this.cardMonthExpire = cardMonthExpire;
    this.cardYearExpire = cardYearExpire;
    this.cvv = cvv;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getCardMonthExpire() {
    return cardMonthExpire;
  }

  public String getCardYearExpire() {
    return cardYearExpire;
  }

  public String getCvv() {
    return cvv;
  }

  public static Card getDefaultCard() {
    return new Card("4111111111111111", "12", "2025", "123");
  }
}
