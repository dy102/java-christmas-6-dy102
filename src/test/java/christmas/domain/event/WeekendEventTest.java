package christmas.domain.event;

import christmas.domain.EventParameter;
import christmas.domain.TotalPrice;
import christmas.domain.UserMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeekendEventTest {
    @DisplayName("주말 할인 이벤트가 적용되는지 확인한다.")
    @Test
    void applyEvent() {
        VisitDate visitDate = new VisitDate(23);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-2"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        WeekendEvent weekendEvent = new WeekendEvent();

        assertThat(weekendEvent.apply(eventParameter)).isEqualTo(-4046);
    }

    @DisplayName("주말(금,토)이 아닐 때 주말 할인 이벤트가 적용되지 않는지 확인한다.")
    @Test
    void applyEvent_2() {
        VisitDate visitDate = new VisitDate(24);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-2"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        WeekendEvent weekendEvent = new WeekendEvent();

        assertThat(weekendEvent.apply(eventParameter)).isZero();
    }
}
