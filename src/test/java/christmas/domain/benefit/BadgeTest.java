package christmas.domain.benefit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {
    @DisplayName("배지를 금액에 맞게 저장하는지 확인한다.")
    @ParameterizedTest
    @MethodSource("validParameters")
    void checkBadgeSetName(int totalEventPrice, String name) {
        Badge badge = new Badge();
        badge.setName(totalEventPrice);
        assertThat(badge.getName()).isEqualTo(name);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(-20000, "산타"),
                Arguments.of(-10000, "트리"),
                Arguments.of(-5000, "별")
        );
    }
}
