package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GiveAwayEventTest {
    @DisplayName("증정 이벤트가 적용되는지 확인한다.")
    @Test
    void applyEvent() {
        GiveAwayEvent giveAwayEvent = new GiveAwayEvent();
        assertThat(giveAwayEvent.isApply(120_000)).isTrue();
    }

    @DisplayName("일정 금액 이하일때 증정 이벤트가 적용되지 않는지 확인한다.")
    @Test
    void applyEvent_2() {
        GiveAwayEvent giveAwayEvent = new GiveAwayEvent();
        assertThat(giveAwayEvent.isApply(119_999)).isFalse();
    }
}
