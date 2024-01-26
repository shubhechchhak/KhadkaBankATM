import java.util.Scanner;
public class ATM {
    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("|---------------------------------------------|");
        System.out.println("            Welcome to the Bank ATM");
        System.out.println("|---------------------------------------------|");
        System.out.print("Please enter your name: ");
        String name = scan.nextLine();
        System.out.print("Please create a PIN: ");
        int pin = scan.nextInt();
        Customer customer = new Customer(name, pin);
        Account checkingAcc = new Account(customer);
        Account savingsAcc = new Account(customer);
        System.out.print("Enter your pin: ");
        int enteredPin = scan.nextInt();
        while (enteredPin != customer.getPin()) {
            System.out.println("The pin you entered is incorrect! Please try again!");
            System.out.print("Enter your pin: ");
            enteredPin = scan.nextInt();
        }
        int option;
        int more = 1;
        while (more == 1) {
            mainMenu();
            System.out.print("Pick an option: ");
            option = scan.nextInt();
            if (option == 1) {
                System.out.println("Which account do you choose to withdraw from?: ");
                String account = scan.nextLine().toLowerCase();
                System.out.println("Enter the amount you would like to withdraw: ");
                int amount = scan.nextInt();
                while (amount % 5 != 0) {
                    System.out.println("You must enter a valid amount! Only multiples of 5 are allowed!");
                    System.out.print("Enter the amount you want to withdraw: ");
                    amount = scan.nextInt();
                }
                if (account.equals("s")) {
                    if (amount > savingsAcc.getMoney()) {

                    }
                }
                System.out.print("Do you want to do anything else (1 = yes, 2 = no): ");
                more = scan.nextInt();
            } else if (option == 2) {
                depositMoney();
                System.out.print("Do you want to do anything else (1 = yes or 2 = no): ");
                more = scan.nextInt();
                if (more == 1) {
                    mainMenu();
                    System.out.print("Pick an option (1-7): ");
                    option = scan.nextInt();
                } else {
                    System.out.println();
                    System.out.println("Thank you for using the ATM! Goodbye!");
                }
            } else if (option == 3) {
                System.out.print("Enter the amount you want to transfer: ");
                int amount = scan.nextInt();
                transferMoney(savingsAcc, checkingAcc, amount);
                System.out.print("Do you want to do anything else (y or n): ");
                more = scan.nextInt();
                if (more == 1) {
                    System.out.print("Pick an option (1-7): ");
                    option = scan.nextInt();
                } else {
                    System.out.println("Thank you for using the ATM! Goodbye!");
                }
            } else if (option == 4) {

            } else if (option == 5) {

            } else if (option == 6) {

            }
        }
    }

    //main menu
    public void mainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Withdraw money");
        System.out.println("2. Deposit money");
        System.out.println("3. Transfer money between accounts");
        System.out.println("4. Get account balances");
        System.out.println("5. Get transaction history");
        System.out.println("6. Change pin");
        System.out.println("7. Exit");
    }

    //withdrawing Money
    public void withdrawMoney() {
        System.out.print("Enter the amount you want to withdraw: ");
        int amount = scan.nextInt();
        scan.nextLine();
        if (!(amount % 5 == 0)){
            System.out.println("You must enter a valid amount! Only multiples of 5 are allowed!");
            System.out.print("Enter the amount you want to withdraw: ");
            amount = scan.nextInt();
            scan.nextLine();
        }
        System.out.println("Pick an account:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int choice = scan.nextInt();
        if (choice == 1) {
            if (amount > checkingAcc.getMoney()) {
                System.out.println("Insufficient funds! You do not have enough money for this withdrawal");
            } else {
                System.out.print("How many $20 bills do you want: ");
                int bigBills = scan.nextInt();
                System.out.print("How many $5 bills do you want: ");
                int smallBills = scan.nextInt();
                checkingAcc.subtractMoney(amount);
                System.out.println("Withdrew $" + amount + " from checking account!");
                System.out.println("You have received " + bigBills + " \"$20 bills\" and " + smallBills + " \"$5 bills\"!");
            }
        } else if (choice == 2) {
            if (amount > savingsAcc.getMoney()) {
                System.out.println("Insufficient funds! You do not have enough money for this withdrawal");
            } else {
                System.out.print("How many $20 bills do you want: ");
                int bigBills = scan.nextInt();
                System.out.print("How many $5 bills do you want: ");
                int smallBills = scan.nextInt();
                savingsAcc.subtractMoney(amount);
                System.out.println("Withdrew $" + amount + " from checking account!");
                System.out.println("You have received " + bigBills + " \"$20 bills and\" " + smallBills + " $5 bills!");
            }
        }
    }

    //depositing money
    public void depositMoney() {
        System.out.println("Enter the amount you want to deposit:");
        int amount = scan.nextInt();
        System.out.println("Pick an account:");
        System.out.println("1. Checking");
        System.out.println("2. Savings");
        int choice = scan.nextInt();
        if (choice == 1) {
            checkingAcc.addMoney(amount);
            System.out.println("Deposited $" + amount + " into checking account! ");
        } else if (choice == 2) {
            savingsAcc.addMoney(amount);
            System.out.println("Deposited $" + amount + " into savings account!");
        }
    }

    //transferring money
    public void transferMoney(Account accFrom, Account accTo, int amount) {
        accFrom.subtractMoney(amount);
        accTo.addMoney(amount);
    }

    //updating pin
    public void updatePin(int newPin) {
        pin = newPin;
        System.out.println("Changed pin!");
    }
}
