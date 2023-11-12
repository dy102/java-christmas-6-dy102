package christmas.domain.event;

import christmas.domain.EventParameter;

import java.util.List;

public class WeekdayEvent implements Event {
    private final List<Integer> weekdays = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26,
            27, 28, 31);

    @Override
    public int apply(EventParameter eventParameter) {
        if (isApply(eventParameter.visitDate().date())) {
            return (-1) * eventParameter.userMenu().countDessert() * 2023;
        }
        return 0;
    }

    @Override
    public boolean isApply(int date) {
        if (weekdays.contains(date)) {
            return true;
        }
        return false;
    }
}
