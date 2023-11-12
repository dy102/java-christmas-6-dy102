package christmas.domain.event;

import christmas.domain.EventParameter;

public interface Event {

    boolean isApply(int number);

    int apply(EventParameter eventParameter);
}
