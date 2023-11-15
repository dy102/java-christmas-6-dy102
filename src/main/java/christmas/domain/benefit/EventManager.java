package christmas.domain.benefit;

import christmas.domain.event.GiveEvent;
import christmas.option.EventName;

public class EventManager {//클래스명 변경 고려
    private final EventPrice eventPrice;

    public EventManager(EventPrice eventPrice) {//맘에안듬. eventParameter를 필드로 가지고 있지 않은데 이렇게 사용하는건...
        // static 변환 방식을 고려해봐야겠다.
        this.eventPrice = eventPrice;
    }

    public void collectAllEvent() {
        EventName[] events = EventName.values();
        for (EventName event : events) {
            calculatePrice(event);
        }
    }

    private void calculatePrice(EventName event) {
        if (event.getEvent() instanceof GiveEvent) {
            eventPrice.calculateServicePrice(event.getEvent());
            return;
        }
        eventPrice.calculateDiscountPrice(event.getEvent());
    }
}
