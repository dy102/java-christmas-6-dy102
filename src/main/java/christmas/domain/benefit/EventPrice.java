package christmas.domain.benefit;

import christmas.domain.event.Event;
import christmas.domain.user.UserInformation;
import christmas.option.EventBundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static christmas.option.EventOption.MINIMUM_PRICE_APPLY_EVENT;

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
        if (userInformation.totalPrice().price() < MINIMUM_PRICE_APPLY_EVENT.getNumber()) {
            return;
        }

        if (event.isApply(userInformation.visitDate().date())) {
            int price = event.apply(userInformation);
            if (price == 0) {
                return;
            }
            appliedEvent.add(EventBundle.of(event).getName());
            appliedPrice.add(price);

            discountPrice += price;
        }
    }

    public void calculateServicePrice(Event event) {
        if (event.isApply(userInformation.totalPrice().price())) {
            appliedEvent.add(EventBundle.of(event).getName());
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
