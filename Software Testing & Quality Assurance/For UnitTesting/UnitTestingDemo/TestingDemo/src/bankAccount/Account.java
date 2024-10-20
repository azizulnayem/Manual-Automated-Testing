package bankAccount;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Account {
    String name;
    String personnummber;
    double balance;

    public Account(String name, String personnummber, double balance) {
        this.name = name;
        this.personnummber = personnummber;
        this.balance = balance;
    }

    public void deposit(double amount){
        if(amount >= 0.0) {
            balance+= amount;
        }
    }

    public void withdraw(double amount) throws BalanceException, InterruptedException {
        if(amount >= 0.0 &&  balance>=amount) {
            TimeUnit.SECONDS.sleep(1);
            balance-= amount;
        }
        else if(balance<amount){
            throw new BalanceException(balance,amount);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPersonnummber() {
        return personnummber;
    }

    public double getBalance() {
        return balance;
    }


}
