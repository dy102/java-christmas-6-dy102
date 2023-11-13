package christmas.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
