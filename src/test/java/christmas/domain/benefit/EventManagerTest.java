package christmas.domain.benefit;

import christmas.domain.user.TotalPrice;
import christmas.domain.user.UserInformation;
import christmas.domain.user.UserMenu;
import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventManagerTest {
    @DisplayName("이벤트 할인 금액을 알맞게 처리하는지 확인한다.")
    @Test
    void checkCalculateAllEventPrice() {
        VisitDate visitDate = new VisitDate(3);
        UserMenu userMenu = UserMenu.form(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(userInformation);
        EventManager eventManager = new EventManager(eventPrice);

        eventManager.collectAllEvent();

        assertThat(eventPrice.getTotalEventPrice()).isEqualTo(-31_246);
    }

}
