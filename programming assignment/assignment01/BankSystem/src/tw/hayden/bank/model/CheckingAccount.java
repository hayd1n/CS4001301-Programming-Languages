package tw.hayden.bank.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import tw.hayden.bank.exception.BankException;
import tw.hayden.bank.time.Time;
import tw.hayden.bank.timeprovider.TimeProvider;

public class CheckingAccount extends Account {
    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("1000");

    public CheckingAccount(String name, BigDecimal initialBalance, BigDecimal rate, TimeProvider timeProvider)
            throws BankException {
        super(name, initialBalance, rate, timeProvider);
        if (initialBalance.compareTo(MINIMUM_BALANCE) < 0) { // initialBalance < MINIMUM_BALANCE
            throw new BankException("Initial balance must be at least $" + MINIMUM_BALANCE);
        }
    }

    @Override
    public void deposit(BigDecimal amount) throws BankException {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    @Override
    public void withdraw(BigDecimal amount) throws BankException {
        validateAmount(amount);
        if (balance.subtract(amount).compareTo(MINIMUM_BALANCE) < 0) {
            throw new BankException("Withdrawal would put balance below minimum");
        }
        balance = balance.subtract(amount);
    }

    @Override
    public void computeInterest() {
        long currentTime = this.timeProvider.currentTimeMillis();
        long daysElapsed = (currentTime - lastInterestComputation) / Time.MILLISECONDS_PER_DAY;
        BigDecimal dailyRate = interestRate.divide(new BigDecimal("365"), 10, RoundingMode.HALF_UP);
        balance = balance.add(balance.multiply(dailyRate).multiply(new BigDecimal(daysElapsed)));
        lastInterestComputation = currentTime;
    }
}