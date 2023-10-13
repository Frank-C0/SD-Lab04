package creditcardservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CreditCardService extends Remote {
    CreditCard createCreditCard(String cardNumber, String cardHolder, double creditLimit) throws RemoteException;

    CreditCard getCreditCard(String cardNumber) throws RemoteException;

    boolean performTransaction(String cardNumber, double amount) throws RemoteException;

    double checkBalance(String cardNumber) throws RemoteException;

    boolean closeCreditCard(String cardNumber) throws RemoteException;

    void addCard(CreditCard card)  throws RemoteException;
} 
