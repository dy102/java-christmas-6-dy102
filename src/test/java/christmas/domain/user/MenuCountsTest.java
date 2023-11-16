package christmas.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_OVER_MAXIMUM_COUNT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuCountsTest {
    @DisplayName("메뉴의 개수를 0개로 입력했을 때 예외가 발생한다.")
    @Test
    void validateCount() {
        assertThatThrownBy(() -> new MenuCounts(List.of(0, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ILLEGAL_ORDER.getMessage());
    }

    @DisplayName("메뉴의 총 개수가 20개 이상일 때 예외가 발생한다.")
    @Test
    void validateCounts() {
        assertThatThrownBy(() -> new MenuCounts(List.of(19, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_OVER_MAXIMUM_COUNT.getMessage());
    }
}
