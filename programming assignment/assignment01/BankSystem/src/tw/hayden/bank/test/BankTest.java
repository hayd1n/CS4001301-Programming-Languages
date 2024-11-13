package tw.hayden.bank.test;

import java.math.BigDecimal;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.model.CDAccount;
import tw.hayden.bank.model.CheckingAccount;
import tw.hayden.bank.model.LoanAccount;
import tw.hayden.bank.model.SavingsAccount;
import tw.hayden.bank.time.Time;
import tw.hayden.bank.timeprovider.MockTimeProvider;

public class BankTest {
    private static final MockTimeProvider timeProvider = new MockTimeProvider(0);
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.12");

    public static void testCheckingAccount() {
        System.out.println("Testing CheckingAccount");
        try {
            timeProvider.setMockTime(0);
            CheckingAccount checking = new CheckingAccount("John Doe", new BigDecimal("2000.0"),
                    INTEREST_RATE, timeProvider);
            System.out.println("Initial balance: " + checking.balance());
            checking.deposit(new BigDecimal("500.0"));
            System.out.println("After deposit: " + checking.balance());
            checking.withdraw(new BigDecimal("300.0"));
            System.out.println("After withdrawal: " + checking.balance());
            timeProvider.setMockTime(Time.MILLISECONDS_PER_DAY);
            checking.computeInterest();
            System.out.println("After interest: " + checking.balance());
        } catch (BankException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testSavingsAccount() {
        System.out.println("Testing SavingsAccount");
        try {
            timeProvider.setMockTime(0);
            SavingsAccount savings = new SavingsAccount("Jane Doe", new BigDecimal("1000.0"), INTEREST_RATE,
                    timeProvider);
            System.out.println("Initial balance: " + savings.balance());
            savings.deposit(new BigDecimal("200.0"));
            System.out.println("After deposit: " + savings.balance());
            savings.withdraw(new BigDecimal("100.0"));
            System.out.println("After withdrawal: " + savings.balance());
            timeProvider.setMockTime(Time.MILLISECONDS_PER_MONTH);
            savings.computeInterest();
            System.out.println("After interest: " + savings.balance());
        } catch (BankException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testCDAccount() {
        System.out.println("Testing CDAccount");
        try {
            timeProvider.setMockTime(0);
            CDAccount cd = new CDAccount("Bob Smith", new BigDecimal("5000.0"), INTEREST_RATE, 12,
                    timeProvider);
            System.out.println("Initial balance: " + cd.balance());
            try {
                cd.deposit(new BigDecimal("1000.0"));
            } catch (BankException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            timeProvider.setMockTime(Time.MILLISECONDS_PER_MONTH);
            cd.computeInterest();
            System.out.println("After interest: " + cd.balance());
        } catch (BankException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testLoanAccount() {
        System.out.println("Testing LoanAccount");
        try {
            timeProvider.setMockTime(0);
            LoanAccount loan = new LoanAccount("Alice Johnson", new BigDecimal("10000.0"), INTEREST_RATE,
                    timeProvider);
            System.out.println("Initial balance: " + loan.balance());
            loan.deposit(new BigDecimal("1000.0"));
            System.out.println("After payment: " + loan.balance());
            try {
                loan.withdraw(new BigDecimal("500.0"));
            } catch (BankException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            timeProvider.setMockTime(Time.MILLISECONDS_PER_MONTH);
            loan.computeInterest();
            System.out.println("After interest: " + loan.balance());
        } catch (BankException e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        testCheckingAccount();
        testSavingsAccount();
        testCDAccount();
        testLoanAccount();
    }
}