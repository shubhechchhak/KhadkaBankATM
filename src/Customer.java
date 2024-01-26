public class Customer {
    private String name;
    private int pin;
    // Customer constructor
    public Customer(String name, int pin) {
        this.name = name;
        this.pin = pin;
    }
    //getter method for pin
    public int getPin() {
        return pin;
    }
    // updating pin
    public void setPin(int newPin) {
        pin = newPin;
    }
}
