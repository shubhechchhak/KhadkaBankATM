public class TransactionHistory {
    private String[] history;
    private Boolean[] isSuccessful;
    public TransactionHistory() {
        history = new String[0];
        isSuccessful = new Boolean[0];
    }

    //uses arrays and a for loop to add the transactions to String [] history and whether it is true or false to isSuccessful
    public void addToHistory(String str, Boolean isSuccessful) {
        String[] strTemp = new String[history.length + 1];
        Boolean[] boolTemp = new Boolean[this.isSuccessful.length + 1];
        for (int i = 0; i < history.length; i++) {
            strTemp[i] = history[i];
            boolTemp[i] = this.isSuccessful[i];
        }
        strTemp[strTemp.length - 1] = str;
        boolTemp[boolTemp.length - 1] = isSuccessful;
        history = strTemp;
        this.isSuccessful = boolTemp;
    }

    //prints all the transaction info
    public String printInfo() {
        String history = "";
        for (int i = 0; i < this.history.length; i++) {
            history += this.history[i] + ":" + isSuccessful[i] + "\n";
        }
        return history;
    }

}
