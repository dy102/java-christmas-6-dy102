package christmas.domain.event;

import christmas.domain.EventParameter;

public interface GiveEvent {

    boolean isApply(int number);

    int apply(EventParameter eventParameter);
}
