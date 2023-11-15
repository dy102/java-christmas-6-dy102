package christmas.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserMenuTest {
    @DisplayName("주문을 메뉴이름, 개수로 잘 나누어 저장하는지 확인한다.")
    @Test
    void checkUserMenuForm() {
        List<String> orders = List.of("양송이수프-1", "제로콜라-2", "크리스마스파스타-1");
        UserMenu userMenu = UserMenu.from(orders);
        assertThat(userMenu.getMenuNames().names()).isEqualTo(List.of("양송이수프", "제로콜라", "크리스마스파스타"));
        assertThat(userMenu.getMenuCounts().counts()).isEqualTo(List.of(1, 2, 1));
    }

    @DisplayName("주문 메뉴에서 메인메뉴와 디저트의 개수를 알맞게 세는지 확인한다.")
    @Test
    void checkCountDessert() {
        List<String> orders = List.of("티본스테이크-2", "양송이수프-1", "아이스크림-2", "초코케이크-1", "크리스마스파스타-1");
        UserMenu userMenu = UserMenu.from(orders);
        assertThat(userMenu.countDessert()).isEqualTo(3);
        assertThat(userMenu.countMain()).isEqualTo(3);
    }
}
