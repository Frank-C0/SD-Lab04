package creditcardservice;

import java.rmi.Naming;

public class CreditCardClient {
    public static void main(String[] args) {
        try {
            CreditCardService service = (CreditCardService) Naming.lookup("CreditCardService");

            CredidCardClientMenu menu = new CredidCardClientMenu(service);
            menu.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 