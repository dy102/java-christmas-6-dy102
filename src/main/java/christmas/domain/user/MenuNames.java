package christmas.domain.user;

import christmas.option.Menu;

import java.util.List;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;

public record MenuNames(List<String> names) {
    public MenuNames {
        validate(names);
    }

    public void validate(List<String> menuNames) {
        validateDuplication(menuNames);
        validateIsExistMenu(menuNames);
        validateOnlyDrink(menuNames);
    }

    private void validateIsExistMenu(List<String> menuNames) {
        menuNames.stream()
                .map(Menu::of);
    }

    private void validateDuplication(List<String> menuNames) {
        if (isDuplication(menuNames)) {
            throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
        }
    }

    private boolean isDuplication(List<String> menuNames) {
        return menuNames.stream()
                .distinct()
                .count() != menuNames.size();
    }

    private void validateOnlyDrink(List<String> menuNames) {
        if (ifOnlyDrink(menuNames)) {
            throw new IllegalArgumentException(NOT_ONLY_DRINK.getMessage());
        }
    }

    private boolean ifOnlyDrink(List<String> menuNames) {
        return menuNames.stream()
                .map(Menu::of)
                .allMatch(menu -> menu.getType().equals("음료"));
    }

    public int size() {
        return names.size();
    }

    public String get(int index) {
        return names.get(index);
    }
}
