package christmas.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TotalPriceTest {
    @DisplayName("주문 메뉴를 이용해서 총금액을 알맞게 계산하는지 확인한다.")
    @Test
    void checkCalculateTotalPrice() {
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-2", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());
        assertThat(totalPrice.price()).isEqualTo(37_000);
    }

    @DisplayName("할인 금액이 적용되는지 확인한다.")
    @Test
    void checkApplyDiscount() {
        UserMenu userMenu = UserMenu.from(List.of("양송이수프-1", "제로콜라-2", "크리스마스파스타-1"));
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());
        totalPrice.applyDiscount(-7_000);
        assertThat(totalPrice.price()).isEqualTo(30_000);
    }
}
