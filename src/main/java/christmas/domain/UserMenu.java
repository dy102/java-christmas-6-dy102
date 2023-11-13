package christmas.domain;

import christmas.option.Menu;
import christmas.validator.UserMenuValidator;
import christmas.view.Converter;

import java.util.List;
import java.util.stream.IntStream;

public class UserMenu {
    private final List<String> menuNames;
    private final List<Integer> menuCount;

    private UserMenu(List<String> menuNames, List<Integer> menuCount) {
        UserMenuValidator.check(menuNames, menuCount);
        this.menuNames = menuNames;
        this.menuCount = menuCount;
    }

    public static UserMenu form(List<String> orders) {
        List<String> menuNames = Converter.orderToMenuNames(orders);
        List<Integer> menuCount = Converter.orderToMenuCount(orders);
        return new UserMenu(menuNames, menuCount);
    }


    public int countDessert() {
        return IntStream.range(0, menuNames.size())
                .filter(i -> Menu.of(menuNames.get(i)).getType().equals("디저트"))
                .map(menuCount::get)
                .sum();
    }

    public int countMain() {
        return IntStream.range(0, menuNames.size())
                .filter(i -> Menu.of(menuNames.get(i)).getType().equals("메인"))
                .map(menuCount::get)
                .sum();
    }

    public List<String> getMenuNames() {
        return menuNames;
    }

    public List<Integer> getMenuCount() {
        return menuCount;
    }
}
