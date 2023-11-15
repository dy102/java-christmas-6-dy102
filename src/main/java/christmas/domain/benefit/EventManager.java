package christmas.domain.benefit;

import christmas.domain.event.GiveEvent;
import christmas.option.EventBundle;

public class EventManager {
    private final EventPrice eventPrice;

    public EventManager(EventPrice eventPrice) {
        this.eventPrice = eventPrice;
    }

    public void collectAllEvent() {
        EventBundle[] events = EventBundle.values();
        for (EventBundle event : events) {
            calculatePrice(event);
        }
    }

    private void calculatePrice(EventBundle event) {
        if (event.getEvent() instanceof GiveEvent) {
            eventPrice.calculateServicePrice(event.getEvent());
            return;
        }
        eventPrice.calculateDiscountPrice(event.getEvent());
    }
}
