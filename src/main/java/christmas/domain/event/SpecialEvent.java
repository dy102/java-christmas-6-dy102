package christmas.domain.event;

import christmas.domain.EventParameter;

import java.util.List;

public class SpecialEvent implements Event {
    private final List<Integer> starDates = List.of(3, 10, 17, 24, 25, 31);

    @Override
    public int apply(EventParameter eventParameter) {
        if (isApply(eventParameter.visitDate().date())) {
            return (-1) * 1000;
        }
        return 0;
    }

    @Override
    public boolean isApply(int date) {
        if (starDates.contains(date)) {
            return true;
        }
        return false;
    }
}
