package creditcardservice;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class CreditCardServiceImpl extends UnicastRemoteObject implements CreditCardService {
    private Map<String, CreditCard> creditCards;

    public CreditCardServiceImpl() throws RemoteException {
        creditCards = new HashMap<>();
    }

    @Override
    public void addCard(CreditCard card)  throws RemoteException {
        creditCards.put(card.getCardNumber(), card);
    }

    @Override
    public CreditCard createCreditCard(String cardNumber, String cardHolder, double creditLimit)
            throws RemoteException {
        CreditCard newCard = new CreditCard(cardNumber, cardHolder, creditLimit);
        creditCards.put(cardNumber, newCard);
        return newCard;
    }

    @Override
    public CreditCard getCreditCard(String cardNumber) throws RemoteException {
        return creditCards.get(cardNumber);
    }
 
    @Override
    public boolean performTransaction(String cardNumber, double amount) throws RemoteException {
        CreditCard card = creditCards.get(cardNumber);
        if (card != null) {
            return card.performTransaction(amount);
        }
        return false;
    }

    @Override
    public double checkBalance(String cardNumber) throws RemoteException {
        CreditCard card = creditCards.get(cardNumber);
        if (card != null) {
            return card.getBalance();
        }
        return -1.0;
    }

    @Override
    public boolean closeCreditCard(String cardNumber) throws RemoteException {
        return creditCards.remove(cardNumber) != null;
    }
}
