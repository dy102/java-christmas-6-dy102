package christmas.domain;

import christmas.validator.UserMenuValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;
import static christmas.option.Error.NOT_OVER_TWENTY;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserMenuValidtorTest {
    @DisplayName("잘못된 값을 입력했을 때 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("inValidParameters")
    void validateIsExistMenu(List<String> names, List<Integer> counts, String message) {
        assertThatThrownBy(() -> UserMenuValidator.check(names, counts))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    static Stream<Arguments> inValidParameters() {//사용하지 않는게 좋으려나? 무슨 검증인지 명시가 제대로 되지 않으니까..
        return Stream.of(
                Arguments.of(List.of("햄버거", "새우튀김"), List.of(1, 2), ILLEGAL_ORDER.getMessage()),
                Arguments.of(List.of("양송이수프", "양송이수프"), List.of(1, 2), ILLEGAL_ORDER.getMessage()),
                Arguments.of(List.of("양송이수프", "제로콜라"), List.of(0, 2), ILLEGAL_ORDER.getMessage()),
                Arguments.of(List.of("양송이수프", "제로콜라"), List.of(22, 2), NOT_OVER_TWENTY.getMessage()),
                Arguments.of(List.of("제로콜라", "레드와인"), List.of(1, 2), NOT_ONLY_DRINK.getMessage())
        );
    }
}
