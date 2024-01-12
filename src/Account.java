public class Account {
    private int money;
    //depositing money
    public void deposit(int newMoney) {
        money += newMoney;
    }
    //withdrawing money
    public void withdraw(int newMoney) {
        money -= newMoney;
    }
    //
}
