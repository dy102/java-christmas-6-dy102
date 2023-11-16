package christmas.domain.event;

import christmas.domain.user.UserInformation;

import java.util.List;

public class WeekendEvent implements Event {
    private final List<Integer> weekends =
            List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

    @Override
    public int apply(UserInformation userInformation) {
        if (isApply(userInformation.visitDate().date())) {
            return (-1) * userInformation.userMenu().countMain() * 2023;
        }
        return 0;
    }

    @Override
    public boolean isApply(int date) {
        if (weekends.contains(date)) {
            return true;
        }
        return false;
    }
}
