package creditcardservice;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CreditCardServer {
    public static void main(String[] args) {
        try {
            CreditCardService service = new CreditCardServiceImpl();
            initializeCreditCardService(service);

            // LocateRegistry.createRegistry(1099); // inicia y registra un servicio RMI en el puerto 1099

            Naming.rebind("CreditCardService", service);

            System.out.println("Servidor RMI listo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeCreditCardService(CreditCardService creditCardService)  throws RemoteException {
        creditCardService.addCard(new CreditCard("11111111", "Frank C", 10000.0));
        creditCardService.addCard(new CreditCard("22222222", "Luis G", 1400.0));
        creditCardService.addCard(new CreditCard("33333333", "Gonzalo", 1400.0));
        creditCardService.addCard(new CreditCard("44444444", "Jhonatan", 2000.0));
    }
}
