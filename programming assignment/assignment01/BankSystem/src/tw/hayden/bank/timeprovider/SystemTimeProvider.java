package tw.hayden.bank.timeprovider;

public class SystemTimeProvider implements TimeProvider {
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
}