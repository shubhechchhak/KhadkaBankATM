public class Account {
    private Customer customer;
    private double money;

    public Account(Customer customer) {
        this.customer = customer;
        money = 0;
    }

    public double getMoney() {
        return money;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }


}
