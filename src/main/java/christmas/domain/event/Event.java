package christmas.domain.event;

import christmas.domain.user.UserInformation;

public interface Event {

    boolean isApply(int number);

    int apply(UserInformation userInformation);
}
