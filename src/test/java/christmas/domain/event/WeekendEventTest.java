package christmas.domain.event;

import christmas.domain.user.TotalPrice;
import christmas.domain.user.UserInformation;
import christmas.domain.user.UserMenu;
import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendEventTest {
    @DisplayName("주말 할인 이벤트가 적용되는지 확인한다.")
    @Test
    void applyEvent() {
        VisitDate visitDate = new VisitDate(23);
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-2"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        WeekendEvent weekendEvent = new WeekendEvent();

        assertThat(weekendEvent.apply(userInformation)).isEqualTo(-4_046);
    }

    @DisplayName("주말(금,토)이 아닐 때 주말 할인 이벤트가 적용되지 않는지 확인한다.")
    @Test
    void unApplyEvent() {
        VisitDate visitDate = new VisitDate(24);
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-2"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        WeekendEvent weekendEvent = new WeekendEvent();

        assertThat(weekendEvent.apply(userInformation)).isZero();
    }
}
