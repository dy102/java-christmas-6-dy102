package christmas.domain.event;

import christmas.domain.EventParameter;

public class GiveAwayEvent implements Event, GiveEvent {

    public int apply(EventParameter eventParameter) {
        if (isApply(eventParameter.totalPrice().price())) {
            return (-1) * 25000;
        }
        return 0;
    }

    public boolean isApply(int price) {
        if (price >= 120000) {
            return true;
        }
        return false;
    }
}
