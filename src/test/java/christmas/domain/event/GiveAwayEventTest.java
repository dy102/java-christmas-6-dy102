package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GiveAwayEventTest {
    @DisplayName("증정 이벤트가 적용되는지 확인한다.")
    @ParameterizedTest
    @MethodSource("validParameters")
    void applyEvent(int price, boolean isApply) {
        GiveAwayEvent giveAwayEvent = new GiveAwayEvent();
        assertThat(giveAwayEvent.isApply(price)).isEqualTo(isApply);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(120_000, true),
                Arguments.of(119_999, false)
        );
    }
}
