package christmas.option;

import christmas.domain.event.DDayEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiveAwayEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.option.EventBundle.D_DAY_EVENT;
import static christmas.option.EventBundle.GIVE_AWAY_EVENT;
import static christmas.option.EventBundle.SPECIAL_EVENT;
import static christmas.option.EventBundle.WEEKDAY_EVENT;
import static christmas.option.EventBundle.WEEKEND_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

class EventBundleTest {
    @DisplayName("이벤트 클래스를 입력했을 때 해당하는 이벤트명을 반환한다.")
    @MethodSource("validParameters")
    @ParameterizedTest
    void inputEventAndReturnEventName(Event eventInstance, String eventName) {
        assertThat(EventBundle.of(eventInstance).getName()).isEqualTo(eventName);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(new DDayEvent(), D_DAY_EVENT.getName()),
                Arguments.of(new WeekdayEvent(), WEEKDAY_EVENT.getName()),
                Arguments.of(new WeekendEvent(), WEEKEND_EVENT.getName()),
                Arguments.of(new SpecialEvent(), SPECIAL_EVENT.getName()),
                Arguments.of(new GiveAwayEvent(), GIVE_AWAY_EVENT.getName())
        );
    }
}
