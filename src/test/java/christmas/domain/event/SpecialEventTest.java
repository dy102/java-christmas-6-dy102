package christmas.domain.event;

import christmas.domain.EventParameter;
import christmas.domain.TotalPrice;
import christmas.domain.UserMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventTest {
    @DisplayName("특별 할인 이벤트가 적용되는지 확인한다.")
    @Test
    void applyEvent() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        SpecialEvent specialEvent = new SpecialEvent();

        assertThat(specialEvent.apply(eventParameter)).isEqualTo(-1000);
    }

    @DisplayName("별이 없는 날에 특별 할인 이벤트가 적용되지 않는지 확인한다.")
    @Test
    void applyEvent_2() {
        VisitDate visitDate = new VisitDate(26);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        SpecialEvent specialEvent = new SpecialEvent();

        assertThat(specialEvent.apply(eventParameter)).isZero();
    }
}
