package christmas.domain.event;

import christmas.domain.user.UserInformation;

public class GiveAwayEvent implements Event, GiveEvent {

    public int apply(UserInformation userInformation) {
        if (isApply(userInformation.totalPrice().price())) {
            return (-1) * 25000;
        }
        return 0;
    }

    public boolean isApply(int price) {
        if (price >= 120000) {
            return true;
        }
        return false;
    }
}
