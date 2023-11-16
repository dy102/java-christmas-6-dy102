package christmas.domain.event;

import christmas.domain.user.UserInformation;

public class DDayEvent implements Event {
    public int apply(UserInformation userInformation) {
        if (isApply(userInformation.visitDate().date())) {
            return (-1) * ((userInformation.visitDate().date() - 1) * 100 + 1000);
        }
        return 0;
    }

    public boolean isApply(int visitDate) {
        if (visitDate <= 25) {
            return true;
        }
        return false;
    }
}
