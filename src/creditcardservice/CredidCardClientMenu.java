package creditcardservice;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CredidCardClientMenu {
    CreditCardService creditCardService;
    boolean inLoop;
    String[] options = {
            "1. Consultar información de tarjeta.",
            "2. Realizar una transacción.",
            "3. Ver el saldo disponible.",
            "4. Eliminar una tarjeta de crédito.",
            "5. Salir."
    };
    private Scanner numberScanner;
    private Scanner strScanner;

    public CredidCardClientMenu(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
        this.inLoop = false;
        numberScanner = new Scanner(System.in);
        strScanner = new Scanner(System.in);

    } 

    public void printOptions() {
        for (String option : options) {
            System.out.println(option);
        }
    }

    public int inputValidInteger(String question, String error) {
        int numero = 0;
        boolean validInteger = false;

        while (!validInteger) {
            try {
                System.out.print(question);
                numero = Integer.parseInt(numberScanner.nextLine());
                validInteger = true;
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
        return numero;
    }

    public double inputValidDouble(String question, String error) {
        double numero = 0;
        boolean validDouble = false;

        while (!validDouble) {
            try {
                System.out.print(question);
                numero = Double.parseDouble(numberScanner.nextLine());
                validDouble = true;
            } catch (NumberFormatException e) {
                System.out.println(error);
            }
        }
        return numero;
    }

    public int getOptionNumber() {
        int optionMax = options.length;
        int optionMin = 0;
        int optionInput = 0;
        while (!(optionInput > optionMin && optionInput <= optionMax)) {
            optionInput = inputValidInteger("\nIngrese una opción valida: ", "Opcion invalida, vuelva a intentar.");
        }
        return optionInput;
    }

    private void enterToContinue() {
        System.out.print("\nPresiona Enter para continuar...");
        strScanner.nextLine();
    }

    public void start() throws RemoteException {
        inLoop = true;
        while (inLoop) {
            System.out.println("\n\nMenú Cliente - Tarjetas de Credito\n");
            printOptions();

            int optionNumber = getOptionNumber();
            String cardNumber;
            switch (optionNumber) {
                case 1:
                    System.out.print("Ingrese el número de tarjeta: ");
                    cardNumber = strScanner.nextLine();
                    CreditCard card = creditCardService.getCreditCard(cardNumber);
                    if (card != null) {
                        System.out.println("Tarjeta: " + card.getCardNumber());
                        System.out.println("Titular: " + card.getCardHolder());
                        System.out.println("Saldo disponible: " + card.getBalance());
                    } else {
                        System.out.println("Tarjeta no encontrada.");
                    }
                    enterToContinue();
                    break;

                case 2:
                    System.out.print("Ingrese el número de tarjeta: ");
                    cardNumber = strScanner.nextLine();
                    double amount = inputValidDouble("Ingrese el monto de la transacción: ",
                            "Ingrese un numbero valido.");
                    if (creditCardService.performTransaction(cardNumber, amount)) {
                        System.out.println("Transacción exitosa.");
                    } else {
                        System.out.println("Transacción fallida. Excede el límite de crédito.");
                    }
                    enterToContinue();
                    break;

                case 3:
                    System.out.print("Ingrese el número de tarjeta: ");
                    cardNumber = strScanner.nextLine();
                    double balance = creditCardService.checkBalance(cardNumber);
                    if (balance >= 0) {
                        System.out.println("Saldo disponible: " + balance);
                    } else {
                        System.out.println("Tarjeta no encontrada.");
                    }
                    enterToContinue();
                    break;

                case 4:
                    System.out.print("Ingrese el número de tarjeta a eliminar: ");
                    cardNumber = strScanner.nextLine();
                    if (creditCardService.closeCreditCard(cardNumber)) {
                        System.out.println("Tarjeta eliminada exitosamente.");
                    } else {
                        System.out.println("Tarjeta no encontrada.");
                    }
                    enterToContinue();
                    break;

                case 5:
                    System.out.println("Saliendo del programa.");
                    stopMenuLoop();

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void stopMenuLoop() {
        inLoop = false;
    }
}
