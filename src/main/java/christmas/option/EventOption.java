package christmas.option;

public enum EventOption {
    START_DATE(1),
    END_DATE(31),
    MINIMUM_BOUND_COUNT(1),
    MAXIMUM_BOUND_COUNT(20),
    EVENT_MONTH(12),
    MINIMUM_PRICE_APPLY_EVENT(10_000);
    private final int number;

    private EventOption(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
