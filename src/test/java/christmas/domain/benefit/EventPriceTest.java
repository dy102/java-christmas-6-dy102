package christmas.domain.benefit;

import christmas.domain.event.DDayEvent;
import christmas.domain.event.GiveAwayEvent;
import christmas.domain.user.TotalPrice;
import christmas.domain.user.UserInformation;
import christmas.domain.user.UserMenu;
import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventPriceTest {
    @DisplayName("이벤트 할인 금액이 알맞게 저장되는지 확인한다.")
    @Test
    void checkDiscountAndSave() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(userInformation);
        DDayEvent dDayEvent = new DDayEvent();

        eventPrice.calculateDiscountPrice(dDayEvent);

        assertThat(eventPrice.getDiscountPrice()).isEqualTo(-3400);
    }

    @DisplayName("이벤트 혜택 금액이 알맞게 저장되는지 확인한다.")
    @Test
    void checkGiveServiceAndSave() {
        VisitDate visitDate = new VisitDate(25);
        UserMenu userMenu = UserMenu.from(List.of("크리스마스파스타-5"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(userInformation);
        GiveAwayEvent giveAwayEvent = new GiveAwayEvent();

        eventPrice.calculateServicePrice(giveAwayEvent);

        assertThat(eventPrice.getServicePrice()).isEqualTo(-25_000);
    }
}
