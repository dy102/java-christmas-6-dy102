package christmas.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.option.EventName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OutputTest extends NsTest {
    @DisplayName("이벤트 혜택 미리보기 문구를 알맞게 출력하는지 확인한다.")
    @Test
    void checkEventIntroduction() {
        Output.eventIntroduction(10);
        assertThat(output()).isEqualTo("12월 10일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    @DisplayName("주문메뉴 문구를 알맞게 출력하는지 확인한다.")
    @Test
    void checkOrderMenu() {
        Output.orderMenu(List.of("양송이수프", "제로콜라"), List.of(1, 2));
        assertThat(output()).isEqualTo("""
                양송이수프 1개
                제로콜라 2개""");
    }

    @DisplayName("금액이 알맞게 표기되는지 확인한다..")
    @Test
    void checkPrice() {
        Output.price(1_000_000_001);
        assertThat(output()).isEqualTo("1,000,000,001원");
    }

    @DisplayName("적용된 이벤트를 알맞게 출력하는지 확인한다.")
    @Test
    void checkAppliedEvent() {
        EventName eventName = EventName.WEEKEND_EVENT;
        List<String> appliedEvent = List.of("주말 할인");
        List<Integer> appliedPrice = List.of(-2023);
        Output.appliedEvent(eventName, appliedEvent, appliedPrice);
        assertThat(output()).isEqualTo("주말 할인: -2,023원");
    }


    @Override
    protected void runMain() {

    }
}
