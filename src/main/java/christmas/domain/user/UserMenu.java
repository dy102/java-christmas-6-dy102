package christmas.domain.user;

import christmas.option.Menu;
import christmas.util.Converter;

import java.util.List;
import java.util.stream.IntStream;

public class UserMenu {
    private final MenuNames menuNames;
    private final MenuCounts menuCounts;

    private UserMenu(List<String> menuNames, List<Integer> menuCounts) {
        this.menuNames = new MenuNames(menuNames);
        this.menuCounts = new MenuCounts(menuCounts);
    }

    public static UserMenu from(List<String> orders) {
        List<String> menuNames = Converter.orderToMenuNames(orders);
        List<Integer> menuCount = Converter.orderToMenuCounts(orders);
        return new UserMenu(menuNames, menuCount);
    }


    public int countDessert() {
        return IntStream.range(0, menuNames.size())
                .filter(i -> Menu.of(menuNames.get(i)).getType().equals("디저트"))
                .map(menuCounts::get)
                .sum();
    }

    public int countMain() {
        return IntStream.range(0, menuNames.size())
                .filter(i -> Menu.of(menuNames.get(i)).getType().equals("메인"))
                .map(menuCounts::get)
                .sum();
    }

    public MenuNames getMenuNames() {
        return menuNames;
    }

    public MenuCounts getMenuCounts() {
        return menuCounts;
    }
}
