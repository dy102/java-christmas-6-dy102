package christmas.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.option.Error.ILLEGAL_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VisitDateTest {
    @DisplayName("올바른 날짜를 입력했을 때 날짜를 저장한다.")
    @Test
    void saveVisitDate() {
        VisitDate visitDate = new VisitDate(31);
        assertThat(visitDate.date()).isEqualTo(31);
    }

    @DisplayName("잘못된 날짜를 입력했을 때 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("inValidParameters")
    void ifInvalidDateThrowException(int date) {
        assertThatThrownBy(() -> new VisitDate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ILLEGAL_DATE.getMessage());
    }

    static Stream<Arguments> inValidParameters() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(32)
        );
    }
}
