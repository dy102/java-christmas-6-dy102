package christmas.option;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.option.Menu.APPETIZER_1;
import static christmas.option.Menu.APPETIZER_2;
import static christmas.option.Menu.APPETIZER_3;
import static christmas.option.Menu.DESSERT_1;
import static christmas.option.Menu.DESSERT_2;
import static christmas.option.Menu.DRINK_1;
import static christmas.option.Menu.DRINK_2;
import static christmas.option.Menu.DRINK_3;
import static christmas.option.Menu.MAIN_1;
import static christmas.option.Menu.MAIN_2;
import static christmas.option.Menu.MAIN_3;
import static christmas.option.Menu.MAIN_4;
import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @DisplayName("이벤트 클래스를 입력했을 때 해당하는 이벤트명을 반환한다.")
    @MethodSource("validParameters")
    @ParameterizedTest
    void inputNameAndReturnMenu(String name, Menu menu) {
        assertThat(Menu.of(name)).isEqualTo(menu);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of("양송이수프", APPETIZER_1),
                Arguments.of("타파스", APPETIZER_2),
                Arguments.of("시저샐러드", APPETIZER_3),
                Arguments.of("티본스테이크", MAIN_1),
                Arguments.of("바비큐립", MAIN_2),
                Arguments.of("해산물파스타", MAIN_3),
                Arguments.of("크리스마스파스타", MAIN_4),
                Arguments.of("초코케이크", DESSERT_1),
                Arguments.of("아이스크림", DESSERT_2),
                Arguments.of("제로콜라", DRINK_1),
                Arguments.of("레드와인", DRINK_2),
                Arguments.of("샴페인", DRINK_3)
        );
    }
}
