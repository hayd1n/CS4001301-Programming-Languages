package tw.hayden.bank.model;

import java.math.BigDecimal;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.timeprovider.TimeProvider;

public abstract class Account {
    protected String accountName;
    protected BigDecimal balance;
    protected BigDecimal interestRate;
    protected long lastInterestComputation;
    protected TimeProvider timeProvider;

    public Account(String name, BigDecimal initialBalance, BigDecimal rate, TimeProvider timeProvider) {
        this.accountName = name;
        this.balance = initialBalance;
        this.interestRate = rate;
        this.timeProvider = timeProvider;

        this.lastInterestComputation = this.timeProvider.currentTimeMillis();
    }

    public String name() {
        return accountName;
    }

    public BigDecimal balance() {
        return balance;
    }

    public abstract void deposit(BigDecimal amount) throws BankException;

    public abstract void withdraw(BigDecimal amount) throws BankException;

    public abstract void computeInterest();

    protected void validateAmount(BigDecimal amount) throws BankException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) { // amount <= 0
            throw new BankException("Amount must be positive");
        }
    }
}