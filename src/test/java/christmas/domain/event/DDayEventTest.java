package christmas.domain.event;

import christmas.domain.EventParameter;
import christmas.domain.TotalPrice;
import christmas.domain.UserMenu;
import christmas.domain.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DDayEventTest {
    @DisplayName("크리스 마스 디데이 할인 이벤트가 적용되는지 확인한다.")
    @Test
    void applyEvent() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        DDayEvent dDayEvent = new DDayEvent();

        assertThat(dDayEvent.apply(eventParameter)).isEqualTo(-3400);
    }

    @DisplayName("기간이 지난 이벤트가 적용되지 않는지 확인한다.")
    @Test
    void applyEvent_2() {
        VisitDate visitDate = new VisitDate(26);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        DDayEvent dDayEvent = new DDayEvent();

        assertThat(dDayEvent.apply(eventParameter)).isZero();
    }
}
