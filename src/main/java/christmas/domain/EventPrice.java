package christmas.domain;

import christmas.domain.event.Event;
import christmas.option.EventName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventPrice {
    private final EventParameter eventParameter;
    private final List<String> appliedEvent = new ArrayList<>();
    private final List<Integer> appliedPrice = new ArrayList<>();

    private int discountPrice = 0;
    private int servicePrice = 0;

    public EventPrice(EventParameter eventParameter) {
        this.eventParameter = eventParameter;
    }

    public void checkDiscount(Event event) {
        if (eventParameter.totalPrice().price() < 10000) {//어디에 넘길지 고민좀..
            return;
        }

        if (event.isApply(eventParameter.visitDate().date())) {
            appliedEvent.add(EventName.of(event).getName());
            int price = event.apply(eventParameter);
            appliedPrice.add(price);

            discountPrice += price;
        }
    }

    public void checkGiveService(Event event) {
        if (event.isApply(eventParameter.totalPrice().price())) {
            appliedEvent.add(EventName.of(event).getName());
            int price = event.apply(eventParameter);
            appliedPrice.add(price);

            servicePrice += price;
        }
    }

    public int getTotalEventPrice() {
        return discountPrice + servicePrice;
    }

    public List<String> getAppliedEvent() {
        return Collections.unmodifiableList(appliedEvent);
    }

    public List<Integer> getAppliedPrice() {
        return Collections.unmodifiableList(appliedPrice);
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public int getServicePrice() {
        return servicePrice;
    }
}
