package christmas.domain;

import christmas.option.Menu;
import christmas.view.Converter;

import java.util.List;
import java.util.stream.IntStream;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;
import static christmas.option.Error.NOT_OVER_TWENTY;

public class UserMenu {
    private final List<String> menuNames;
    private final List<Integer> menuCount;

    private UserMenu(List<String> menuNames, List<Integer> menuCount) {
        this.menuNames = menuNames;
        this.menuCount = menuCount;
        validateDuplication();
        validateisExistMenu();
        validateCount();
        validateCounts();
        validateOnlyDrink();
    }

    public static UserMenu form(List<String> orders){
        List<String> menuNames = Converter.orderToMenuNames(orders);
        List<Integer> menuCount = Converter.orderToMenuCount(orders);
        return new UserMenu(menuNames,menuCount);
    }

    public void validateisExistMenu() {
        for (int i = 0; i < menuNames.size(); i++) {
            Menu.of(menuNames.get(i));
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

    public void validateCount(){
        for(int i = 0; i < menuCount.size(); i++){
            if(menuCount.get(i)<1){
                throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
            }
        }
    }

    public void validateCounts(){
        int totalCount = menuCount.stream().mapToInt(Integer::intValue).sum();
        if(totalCount>20){
            throw new IllegalArgumentException(NOT_OVER_TWENTY.getMessage());
        }
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
