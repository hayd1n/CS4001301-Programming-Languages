package tw.hayden.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.time.Time;
import tw.hayden.bank.timeprovider.TimeProvider;

public class CDAccount extends Account {
    private static final BigDecimal EARLY_WITHDRAWAL_FEE = new BigDecimal("250");
    private final long maturityDate;
    private final BigDecimal initialDeposit;

    public CDAccount(String name, BigDecimal amount, BigDecimal rate, int monthsDuration, TimeProvider timeProvider)
            throws BankException {
        super(name, amount, rate, timeProvider);
        validateAmount(amount);
        this.initialDeposit = amount;
        this.maturityDate = this.timeProvider.currentTimeMillis() + (long) monthsDuration * 30 * 24 * 60 * 60 * 1000;
    }

    @Override
    public void deposit(BigDecimal amount) throws BankException {
        throw new BankException("Cannot deposit to CD account");
    }

    @Override
    public void withdraw(BigDecimal amount) throws BankException {
        validateAmount(amount);
        if (this.timeProvider.currentTimeMillis() < maturityDate) {
            // if (balance - amount - EARLY_WITHDRAWAL_FEE < 0) {
            if (balance.subtract(amount).subtract(EARLY_WITHDRAWAL_FEE).compareTo(BigDecimal.ZERO) < 0) {
                throw new BankException("Insufficient funds including withdrawal fee");
            }
            // balance -= (amount + EARLY_WITHDRAWAL_FEE);
            balance = balance.subtract(amount.add(EARLY_WITHDRAWAL_FEE));
        } else {
            // if (balance < amount) {
            if (balance.compareTo(amount) < 0) {
                throw new BankException("Insufficient funds");
            }
            // balance -= amount;
            balance = balance.subtract(amount);
        }
    }

    @Override
    public void computeInterest() {
        long currentTime = this.timeProvider.currentTimeMillis();
        if (currentTime <= maturityDate &&
                currentTime - lastInterestComputation >= Time.MILLISECONDS_PER_MONTH) {
            BigDecimal monthlyRate = interestRate.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);
            balance = balance.add(initialDeposit.multiply(monthlyRate));
            lastInterestComputation = currentTime;
        }
    }
}