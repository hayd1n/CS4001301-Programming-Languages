package tw.hayden.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.time.Time;
import tw.hayden.bank.timeprovider.TimeProvider;

public class SavingsAccount extends Account {
    private static final BigDecimal TRANSACTION_FEE = new BigDecimal("1.0");
    private static final int FREE_TRANSACTIONS_PER_MONTH = 3;
    private int transactionsThisMonth;
    private long lastMonthReset;

    public SavingsAccount(String name, BigDecimal initialBalance, BigDecimal rate, TimeProvider timeProvider) {
        super(name, initialBalance, rate, timeProvider);
        this.transactionsThisMonth = 0;
        this.lastMonthReset = this.timeProvider.currentTimeMillis();
    }

    private void checkAndResetMonthlyTransactions() {
        long currentTime = this.timeProvider.currentTimeMillis();
        if (currentTime - lastMonthReset >= 30L * 24 * 60 * 60 * 1000) {
            transactionsThisMonth = 0;
            lastMonthReset = currentTime;
        }
    }

    @Override
    public void deposit(BigDecimal amount) throws BankException {
        validateAmount(amount);
        checkAndResetMonthlyTransactions();
        // balance += amount;
        balance = balance.add(amount);
        if (transactionsThisMonth >= FREE_TRANSACTIONS_PER_MONTH) {
            // balance -= TRANSACTION_FEE;
            balance = balance.subtract(TRANSACTION_FEE);
        }
        transactionsThisMonth++;
    }

    @Override
    public void withdraw(BigDecimal amount) throws BankException {
        validateAmount(amount);
        checkAndResetMonthlyTransactions();
        // if (balance < amount) {
        if (balance.compareTo(amount) < 0) {
            throw new BankException("Insufficient funds");
        }
        // balance -= amount;
        balance = balance.subtract(amount);
        if (transactionsThisMonth >= FREE_TRANSACTIONS_PER_MONTH) {
            // balance -= TRANSACTION_FEE;
            balance = balance.subtract(TRANSACTION_FEE);
        }
        transactionsThisMonth++;
    }

    @Override
    public void computeInterest() {
        long currentTime = this.timeProvider.currentTimeMillis();
        if (currentTime - lastInterestComputation >= Time.MILLISECONDS_PER_MONTH) {
            // BigDecimal monthlyRate = interestRate / 12.0;
            BigDecimal monthlyRate = interestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);
            // balance += balance * monthlyRate;
            balance = balance.add(balance.multiply(monthlyRate));
            lastInterestComputation = currentTime;
        }
    }
}