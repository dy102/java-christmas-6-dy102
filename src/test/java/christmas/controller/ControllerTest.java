package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerTest extends NsTest {
    @DisplayName("잘못된 날짜를 입력했을 때 오류 문구를 띄우고 재입력 받는다.")
    @Test
    void ifInvalidDateReInput() {
        runException("260", "숫자가 아님");
        assertThat(output()).contains("[ERROR]");
    }

    @DisplayName("잘못된 주문 형식을 입력했을 때 오류 문구를 띄우고 재입력 받는다.")
    @Test
    void ifInValidOrderReInput() {
        runException("26", "잘못된 주문 형식", "로제쉬림프파스타-1", "양송이스프-1,양송이스프-1");
        assertThat(output()).contains("[ERROR]");
    }

    @DisplayName("각종 잘못된 입력 형식에 대해 예외를 처리하는지 확인한다.")
    @ParameterizedTest
    @MethodSource("inValidParameters")
    void ifInvalidInputCatchException(String date, String order) {
        runException(date, order);
        assertThat(output()).contains("[ERROR]");
    }

    static Stream<Arguments> inValidParameters() {
        return Stream.of(
                Arguments.of("0", "양송이수프-1"),
                Arguments.of("-", "양송이수프-1"),
                Arguments.of("32", "양송이수프-1"),
                Arguments.of("00", "양송이수프-1"),
                Arguments.of("032", "양송이수프-1"),
                Arguments.of("000000000000000000", "양송이수프-1"),
                Arguments.of("문자", "양송이수프-1"),
                Arguments.of("english", "양송이수프-1"),
                Arguments.of("3", "양송이수프-"),
                Arguments.of("3", "양송이수프-2-2"),
                Arguments.of("3", "양송이수프-1,,바비큐립-1"),
                Arguments.of("3", "-"),
                Arguments.of("3", "-2"),
                Arguments.of("3", "양송이수프.1"),
                Arguments.of("3", "양송이수프--1")
        );
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
