import java.awt.*;
import java.util.Objects;
import java.util.Scanner;
public class ATM {
    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println(Colors.PURPLE + "|---------------------------------------------|");
        System.out.println(Colors.PURPLE + "            Welcome to the Bank ATM");
        System.out.println(Colors.PURPLE + "|---------------------------------------------|" + Colors.RESET);
        System.out.println();
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.print("Please create a PIN: ");
        int pin = scan.nextInt();
        Customer customer = new Customer(name, pin);
        Account checkingAcc = new Account(customer);
        Account savingsAcc = new Account(customer);
        TransactionHistory history = new TransactionHistory();
        System.out.print("Enter your PIN: ");
        int enteredPin = scan.nextInt();
        while (enteredPin != customer.getPin()) {
            System.out.println(Colors.RED + "The pin you entered is incorrect! Please try again!" + Colors.RESET);
            System.out.print("Enter your PIN: ");
            enteredPin = scan.nextInt();
        }
        int option;
        int more = 1;
        while (more == 1) {
            System.out.println();
            mainMenu();
            System.out.println();
            System.out.print("Pick an option: ");
            option = scan.nextInt();
            System.out.println();
            if (option == 1) {
                System.out.print("Which account do you choose to withdraw from? (s = 1 or c = 2): ");
                int account = scan.nextInt();
                System.out.print("Enter the amount you would like to withdraw: ");
                int amount = scan.nextInt();
                while (amount % 5 != 0) {
                    System.out.println(Colors.RED + "You must enter a valid amount! Only multiples of 5 are allowed!" + Colors.RESET);
                    System.out.print("Enter the amount you want to withdraw: ");
                    amount = scan.nextInt();
                }
                if (account == 1) {
                    if (amount > savingsAcc.getMoney()) {
                        System.out.println(Colors.RED + "Insufficient funds! You do not have enough money for this withdrawal" + Colors.RESET);
                        history.addToHistory("Withdrew money from savings account", false);
                    } else {
                        System.out.print("How many twenties would you like to receive: ");
                        int twenties = scan.nextInt();
                        System.out.print("How many fives would you like to receive: ");
                        int fives = scan.nextInt();
                        System.out.println(Colors.GREEN + "You have received " + twenties + " twenties and " + fives + " fives!");
                        System.out.println(Colors.GREEN + "Transaction complete!" + Colors.RESET);
                        history.addToHistory("Withdrew money from savings account", true);
                    }
                } else {
                    if (amount > checkingAcc.getMoney()) {
                        System.out.println(Colors.RED + "Insufficient funds! You do not have enough money for this withdrawal" + Colors.RESET);
                        history.addToHistory("Withdrew money from checking account", false);
                    } else {
                        System.out.print("How many twenties would you like to receive: ");
                        int twenties = scan.nextInt();
                        System.out.print("How many fives would you like to receive: ");
                        int fives = scan.nextInt();
                        System.out.println(Colors.GREEN + "You have received " + twenties + " twenties and " + fives + " fives!");
                        System.out.println(Colors.GREEN + "Transaction complete!" + Colors.RESET);
                        history.addToHistory("Withdrew money from checking account", true);
                    }
                }
            } else if (option == 2) {
                System.out.print("Which account do you choose to deposit to? (s = 1 or c = 2): ");
                int account = scan.nextInt();
                System.out.print("Enter the amount you would like to deposit: ");
                double amount = scan.nextDouble();
                if (account == 1) {
                    savingsAcc.addMoney(amount);
                    history.addToHistory("Deposited money into savings account", true);
                } else {
                    checkingAcc.addMoney(amount);
                    history.addToHistory("Deposited money into checking account", true);
                }
                System.out.println(Colors.GREEN + "Transaction complete!" + Colors.RESET);
            } else if (option == 3) {
                System.out.print("Pick an account to transfer from (s = 1 or c = 2): ");
                int account = scan.nextInt();
                System.out.print("Enter the amount you want to transfer: ");
                double amount = scan.nextInt();
                if (account == 1) {
                    if (amount > savingsAcc.getMoney()) {
                        System.out.println(Colors.RED + "Insufficient funds! You do not have enough money in your savings" + Colors.RESET);
                        history.addToHistory("Transferred money from savings account into checking account", false);
                    } else {
                        savingsAcc.subtractMoney(amount);
                        checkingAcc.addMoney(amount);
                        history.addToHistory("Transferred money from savings account into checking account", true);
                        System.out.println(Colors.GREEN + "Transaction complete!" + Colors.RESET);
                    }
                } else {
                    if (amount > checkingAcc.getMoney()) {
                        System.out.println(Colors.RED + "Insufficient funds! You do not have enough money in your checking" + Colors.RESET);
                        history.addToHistory("Transferred money from checking account into savings account", false);
                    } else {
                        checkingAcc.subtractMoney(amount);
                        savingsAcc.addMoney(amount);
                        history.addToHistory("Transferred money from checking account into savings account", true);
                        System.out.println(Colors.GREEN + "Transaction complete!" + Colors.RESET);
                    }
                }
            } else if (option == 4) {
                System.out.println(Colors.BLUE + "Your account balances:\nSavings account: " + savingsAcc.getMoney() + "\nChecking account: " + checkingAcc.getMoney() + Colors.RESET);
                history.addToHistory("Checked account balances", true);
            } else if (option == 5) {
                System.out.println(Colors.YELLOW + history.printInfo() + Colors.RESET);
                history.addToHistory("Checked transaction history", true);
            } else if (option == 6) {
                System.out.print("Enter a new PIN: ");
                int newPIN = scan.nextInt();
                customer.setPin(newPIN);
                System.out.println(Colors.GREEN + "PIN successfully changed!" + Colors.RESET);
                history.addToHistory("Changed PIN", true);
                System.out.print("Enter your PIN: ");
                enteredPin = scan.nextInt();
                while (enteredPin != customer.getPin()) {
                    System.out.println("The pin you entered is incorrect! Please try again!");
                    System.out.print("Enter your PIN: ");
                    enteredPin = scan.nextInt();
                }
            } else if (option == 7) {
                more = 2;
            }
        }
        System.out.println(Colors.PURPLE + "Thank you for using the ATM! Have a nice day!" + Colors.RESET);
    }

    //main menu
    public void mainMenu() {
        System.out.println(Colors.CYAN + "Main Menu:");
        System.out.println(Colors.CYAN +"1. Withdraw money");
        System.out.println(Colors.CYAN +"2. Deposit money");
        System.out.println(Colors.CYAN +"3. Transfer money between accounts");
        System.out.println(Colors.CYAN +"4. Get account balances");
        System.out.println(Colors.CYAN +"5. Get transaction history");
        System.out.println(Colors.CYAN +"6. Change pin");
        System.out.println(Colors.CYAN +"7. Exit" + Colors.RESET);
    }
}
