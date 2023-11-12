package christmas.domain;

import christmas.domain.event.Event;
import christmas.domain.event.GiveEvent;
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
        if (eventParameter.totalPrice().price() < 10000) {
            return;
        }
        if (event.isApply(eventParameter.visitDate().date())) {
            appliedEvent.add(EventName.of(event).getName());
            appliedPrice.add(event.apply(eventParameter));

            discountPrice += event.apply(eventParameter);
        }
    }

    public void checkGiveService(Event event) {
        if (event.isApply(eventParameter.totalPrice().price())) {
            appliedEvent.add(EventName.of(event).getName());
            appliedPrice.add(event.apply(eventParameter));

            servicePrice += event.apply(eventParameter);
        }
    }

    public int getTotalEventPrice(){
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