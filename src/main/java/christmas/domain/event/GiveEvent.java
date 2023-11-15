package christmas.domain.event;

import christmas.domain.user.UserInformation;

public interface GiveEvent {

    boolean isApply(int number);

    int apply(UserInformation userInformation);
}
