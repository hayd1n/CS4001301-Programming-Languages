package tw.hayden.bank.timeprovider;

public class MockTimeProvider implements TimeProvider {
    private long mockTime;

    public MockTimeProvider(long initialTime) {
        this.mockTime = initialTime;
    }

    public void setMockTime(long mockTime) {
        this.mockTime = mockTime;
    }

    @Override
    public long currentTimeMillis() {
        return mockTime;
    }
}