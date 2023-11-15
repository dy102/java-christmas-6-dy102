package christmas.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuNamesTest {
    @DisplayName("존재하지 않는 메뉴이름을 입력했을 때 예외가 발생한다.")
    @Test
    void validateIsExistMenu() {
        assertThatThrownBy(() -> new MenuNames(List.of("햄버거", "새우튀김")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ILLEGAL_ORDER.getMessage());
    }

    @DisplayName("같은 메뉴이름을 여러번 입력했을 때 예외가 발생한다.")
    @Test
    void validateIsDuplication() {
        assertThatThrownBy(() -> new MenuNames(List.of("햄버거", "새우튀김")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ILLEGAL_ORDER.getMessage());
    }

    @DisplayName("주문 메뉴에 음료만 입력했을 때 예외가 발생한다.")
    @Test
    void validateOnlyDrink() {
        assertThatThrownBy(() -> new MenuNames(List.of("제로콜라", "레드와인")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ONLY_DRINK.getMessage());
    }
}
