package bankAccountTest;

import bankAccount.Account;
import bankAccount.BalanceException;
import bankAccount.NegativeAmountException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    @Test
    public void testWithdraw_Normal() throws BalanceException, InterruptedException {
        // Setup
        Account account = new Account("Test MrTest", "19850101-1001", 48.5);// Test Steps
        double toWithdraw = 16.0; //Input
        account.withdraw(toWithdraw);
        double actual = account.getBalance();
        double expectedBalance = 32.5; // Oracle
        assertEquals(expectedBalance, actual); // Oracle

    }

    @Test
    public void testWithdraw_MoreThanBalance(){

            // Setup
            Account account = new Account("Test MrTest", "19850101-1001", 48.5);// Test Steps
            // Test Steps
            double toWithdraw = 100.0; //Input
            Throwable exception = assertThrows(BalanceException.class,
                    () -> { account.withdraw(toWithdraw); });
            assertEquals("Existing Balance 48.50 is less than amount:100.00",exception.getMessage()); // Oracle


    }
    @Test
    public void testdemo(){

    }

    @BeforeAll
    public static void init(){
        System.out.println("Starting testing");
    }
    @AfterAll
    public static void teardown(){
        System.out.println("Ending testing");
    }
    @AfterEach
    public void testDemo(){
        System.out.println("here");
    }

    @Test
    public void testAssertTrue(){

        boolean flag  = false;

        assertTrue(flag);
    }

    @Test
    public void testTimeOut() throws BalanceException {
        Account account = new Account("Test MrTest", "19850101-1001", 48.5);// Test Steps
        double toWithdraw = 16.0; //Input

        assertTimeout(Duration.ofMillis(10),
                () -> { account.withdraw(toWithdraw); });

    }


}
