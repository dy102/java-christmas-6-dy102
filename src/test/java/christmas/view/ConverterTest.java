package christmas.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static christmas.option.Error.ILLEGAL_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConverterTest {
    @DisplayName("숫자를 입력했을 때 String을 Int형으로 바꿔주는지 확인한다.")
    @Test
    void convertStringToInt() {
        assertThat(Converter.stringToInt("3")).isEqualTo(3);
    }

    @DisplayName("숫자가 아닌 값을 입력했을 때 예외가 발생한다.")
    @ValueSource(strings = {"숫자가 아닌 값", "10n", "...", "", " "})
    @ParameterizedTest
    void inputNotNumberThrowException(String notNumber) {
        assertThatThrownBy(() -> Converter.stringToInt(notNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ILLEGAL_DATE.getMessage());
    }

    @DisplayName("주문 정보를 구분자 기준으로 잘 구분하는지 확인한다.")
    @Test
    void convertStringToList() {
        assertThat(Converter.stringToList("양송이수프-1,제로콜라-1")).isEqualTo(List.of("양송이수프-1", "제로콜라-1"));
    }

    @DisplayName("주문 정보의 구분자를 기준으로 메뉴 이름 List를 알맞게 생성하는지 확인한다.")
    @Test
    void convertOrderOtMenuNames() {
        assertThat(Converter.orderToMenuNames(List.of("양송이수프-1", "제로콜라-1"))).isEqualTo(List.of("양송이수프", "제로콜라"));
    }

    @DisplayName("주문 정보의 구분자를 기준으로 메뉴 개수 List를 알맞게 생성하는지 확인한다.")
    @Test
    void convertOrderOtMenuCount() {
        assertThat(Converter.orderToMenuCount(List.of("양송이수프-1", "제로콜라-2"))).isEqualTo(List.of(1, 2));
    }
}
