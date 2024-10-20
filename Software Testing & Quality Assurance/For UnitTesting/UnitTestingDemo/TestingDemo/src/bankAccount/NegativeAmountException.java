package bankAccount;

public class NegativeAmountException extends Exception{
    public NegativeAmountException(double amount) {
        super("Amount "+String.format("%.2f", amount)+" is Invalid for this operation");
    }
}
