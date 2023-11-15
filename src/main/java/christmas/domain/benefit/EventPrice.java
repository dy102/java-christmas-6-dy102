package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.user.UserInformation;
import christmas.option.EventName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventPrice {
    private final UserInformation userInformation;
    private final List<String> appliedEvent = new ArrayList<>();
    private final List<Integer> appliedPrice = new ArrayList<>();

    private int discountPrice = 0;
    private int servicePrice = 0;

    public EventPrice(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public void calculateDiscountPrice(Event event) {
        if (userInformation.totalPrice().price() < 10000) {//어디에 넘길지 고민좀..
            return;
        }

        if (event.isApply(userInformation.visitDate().date())) {
            appliedEvent.add(EventName.of(event).getName());
            int price = event.apply(userInformation);
            appliedPrice.add(price);

            discountPrice += price;
        }
    }

    public void calculateServicePrice(Event event) {
        if (event.isApply(userInformation.totalPrice().price())) {
            appliedEvent.add(EventName.of(event).getName());
            int price = event.apply(userInformation);
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
