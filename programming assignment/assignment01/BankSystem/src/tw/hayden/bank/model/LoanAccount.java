package tw.hayden.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.time.Time;
import tw.hayden.bank.timeprovider.TimeProvider;

public class LoanAccount extends Account {
    public LoanAccount(String name, BigDecimal loanAmount, BigDecimal rate, TimeProvider timeProvider)
            throws BankException {
        // super(name, -loanAmount, rate);
        super(name, loanAmount.negate(), rate, timeProvider);
        validateAmount(loanAmount);
    }

    @Override
    public void deposit(BigDecimal amount) throws BankException {
        validateAmount(amount);
        // balance += amount;
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) throws BankException {
        throw new BankException("Cannot withdraw from loan account");
    }

    @Override
    public void computeInterest() {
        long currentTime = this.timeProvider.currentTimeMillis();
        if (currentTime - lastInterestComputation >= Time.MILLISECONDS_PER_MONTH) {
            // BigDecimal monthlyRate = interestRate / 12.0;
            BigDecimal monthlyRate = interestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);
            // balance -= Math.abs(balance) * monthlyRate;
            balance = balance.subtract(balance.abs().multiply(monthlyRate));
            lastInterestComputation = currentTime;
        }
    }
}