public class Account {
    private Customer customer;
    private double money;

    public Account(Customer customer) {
        this.customer = customer;
        money = 0;
    }

    //returns account balance
    public double getMoney() {
        return money;
    }

    //used during withdrawals and transfers
    public void subtractMoney(double amount) {
        money -= amount;
    }

    //used during deposits and transfers
    public void addMoney(double amount) {
        money += amount;
    }


}
