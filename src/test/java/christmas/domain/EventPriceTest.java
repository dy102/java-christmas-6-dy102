package christmas.domain;

import christmas.domain.event.DDayEvent;
import christmas.domain.event.GiveAwayEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventPriceTest {
    @DisplayName("이벤트 할인 금액이 알맞게 저장되는지 확인한다.")
    @Test
    void checkDiscountAndSave() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.form(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(eventParameter);
        DDayEvent dDayEvent = new DDayEvent();

        eventPrice.checkDiscount(dDayEvent);

        assertThat(eventPrice.getDiscountPrice()).isEqualTo(-3400);
    }

    @DisplayName("이벤트 혜택 금액이 알맞게 저장되는지 확인한다.")
    @Test
    void checkGiveServiceAndSave() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.form(List.of("크리스마스파스타-5"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(eventParameter);
        GiveAwayEvent giveAwayEvent = new GiveAwayEvent();

        eventPrice.checkGiveService(giveAwayEvent);

        assertThat(eventPrice.getServicePrice()).isEqualTo(-25_000);
    }
}
