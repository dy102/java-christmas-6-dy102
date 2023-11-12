package christmas.domain.event;

import christmas.domain.EventParameter;

public class DDayEvent implements Event {
    public int apply(EventParameter eventParameter) {
        if (isApply(eventParameter.visitDate().date())) {
            return (-1) * ((eventParameter.visitDate().date() - 1) * 100 + 1000);
        }
        return 0;
    }

    public boolean isApply(int visitDate) {
        if (visitDate <= 25) {
            return true;
        }
        return false;
    }
}
