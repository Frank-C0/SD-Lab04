package creditcardservice;

import java.io.Serializable;
 
public class CreditCard implements Serializable {
    private String cardNumber;
    private String cardHolder;
    private double creditLimit;
    private double balance;

    public CreditCard(String cardNumber, String cardHolder, double creditLimit) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
        this.balance = 0.0;
    }
    public boolean performTransaction(double amount) {
        if (balance + amount <= creditLimit) {
            balance += amount;
            return true;
        }
        return false;
    }
    public double getBalance() {
        return balance;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public String getCardHolder() {
        return cardHolder;
    }
}
