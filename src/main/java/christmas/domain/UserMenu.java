package christmas.domain;

import christmas.option.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;

public class UserMenu {
    private final List<String> orders;
    private final List<String> menuNames = new ArrayList<>();
    private final List<Integer> menuCount = new ArrayList<>();

    public UserMenu(List<String> orders) {
        this.orders = orders;
        setMenuNames();
        setMenuCount();
        validateDuplication();
        validateisExistMenu();
        validateOnlyDrink();
    }

    public void validateisExistMenu() {
        for (int i = 0; i < orders.size(); i++) {
            Menu.of(menuNames.get(i));
        }
    }

    private void setMenuNames() {
        for (String order : orders) {
            Arrays.stream(order.split("-")).findFirst().ifPresent(menuNames::add); //Converter역할 고려
            //비었는지 안비었는지 검증필요
        }
    }

    private void setMenuCount() {
        for (String order : orders) {
            List<String> splitOrder = Arrays.stream(order.split("-")).toList();
            try {
                menuCount.add(Integer.parseInt(splitOrder.get(1)));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
            }
            //비었는지 안비었는지 검증필요 //count 검증과정 필요함.
        }
    }

    public void validateDuplication() {
        if (isDuplication()) {
            throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
        }
    }

    public boolean isDuplication() {
        return menuNames.stream()
                .distinct()
                .count() != menuNames.size();
    }

    public void validateOnlyDrink() {
        if (ifOnlyDrink()) {
            throw new IllegalArgumentException(NOT_ONLY_DRINK.getMessage());
        }
    }

    public boolean ifOnlyDrink() { //클래스 분리 고려(menuNames)
        return menuNames.stream()
                .map(Menu::of)
                .allMatch(menu -> menu.getType().equals("음료"));
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
