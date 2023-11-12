package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiveEvent;
import christmas.option.EventName;

public class EventManager {//클래스명 변경 고려
    private final EventPrice eventPrice;

    public EventManager(EventPrice eventPrice) {//맘에안듬. eventParameter를 필드로 가지고 있지 않은데 이렇게 사용하는건...
        // static 변환 방식을 고려해봐야겠다.
        this.eventPrice = eventPrice;
    }

    public void collectAllEvent(TotalPrice totalPrice) {
        if (totalPrice.price() < 10000) {//어디에 넘길지 고민좀..
            return;
        }
        EventName[] events = EventName.values();
        for (EventName event : events) {
            calculatePrice(event);
        }
        //이벤트가 새로 생길때마다 여기 넣어주면 번거로울 것 같은데..
        // enum을 이용해 알아서 배정해주는 방법을 쓰면 안될까?? service유무는 true,false로 관리하면 되고.
        // 이렇게 하면 새로운 이벤트가 생기거나, 새해 이벤트를 위해 이벤트를 갈아끼울 때 이곳까지 건들지 않아도 되겠지.
    }

    private void calculatePrice(EventName event) { //메서드명 변경하는게 좋을 것 같다. 계산이라니 이상함.. 역할분리 고민
        if (event.getEvent() instanceof GiveEvent) {
            calculateServicePrice(event.getEvent());
            return;
        }
        calculateDiscountPrice(event.getEvent());
    }

    public void calculateDiscountPrice(Event event) {
        eventPrice.checkDiscount(event);
    }

    public void calculateServicePrice(Event giveEvent) {
        eventPrice.checkGiveService(giveEvent);
    }
}
