package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventManagerTest {
    @DisplayName("이벤트 할인 금액이 알맞게 저장되는지 확인한다.")
    @Test
    void checkDiscountAndSave() {//다른메서드도 해야하나?
        VisitDate visitDate = new VisitDate(3);
        UserMenu userMenu = UserMenu.form(List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(eventParameter);
        EventManager eventManager = new EventManager(eventPrice);

        eventManager.collectAllEvent(totalPrice);

        assertThat(eventPrice.getTotalEventPrice()).isEqualTo(-31_246);
    }

}
