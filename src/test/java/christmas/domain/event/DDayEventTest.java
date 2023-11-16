package christmas.domain.event;

import christmas.domain.user.TotalPrice;
import christmas.domain.user.UserInformation;
import christmas.domain.user.UserMenu;
import christmas.domain.user.VisitDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class DDayEventTest {
    @DisplayName("크리스 마스 디데이 할인 이벤트가 적용되는지 확인한다.")
    @ParameterizedTest
    @MethodSource("validParameters")
    void applyEvent(int date, int price) {
        VisitDate visitDate = new VisitDate(date);
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-1", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        DDayEvent dDayEvent = new DDayEvent();

        assertThat(dDayEvent.apply(userInformation)).isEqualTo(price);
    }

    static Stream<Arguments> validParameters() {
        return Stream.of(
                Arguments.of(25, -3_400),
                Arguments.of(26, 0)
        );
    }
}
