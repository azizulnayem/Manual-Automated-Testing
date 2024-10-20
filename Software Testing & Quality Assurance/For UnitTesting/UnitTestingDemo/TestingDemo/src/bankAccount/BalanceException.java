package bankAccount;

public class BalanceException extends Exception{
    public BalanceException(double existingBalance, double amount ) {
        super("Existing Balance "+ String.format("%.2f", existingBalance) + " is less than amount:"+String.format("%.2f", amount) );
    }
}
