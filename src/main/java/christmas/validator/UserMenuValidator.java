package christmas.validator;

import christmas.option.Menu;

import java.util.List;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_ONLY_DRINK;
import static christmas.option.Error.NOT_OVER_TWENTY;

public class UserMenuValidator {
    private UserMenuValidator() {
    }

    public static void check(List<String> menuNames, List<Integer> menuCount) {
        validateDuplication(menuNames);
        validateIsExistMenu(menuNames);
        validateCount(menuCount);
        validateCounts(menuCount);
        validateOnlyDrink(menuNames);
    }

    private static void validateIsExistMenu(List<String> menuNames) {
        menuNames.stream()
                .map(Menu::of);
    }

    private static void validateDuplication(List<String> menuNames) {
        if (isDuplication(menuNames)) {
            throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
        }
    }

    private static boolean isDuplication(List<String> menuNames) {
        return menuNames.stream()
                .distinct()
                .count() != menuNames.size();
    }

    private static void validateCount(List<Integer> menuCount) {
        for (int i = 0; i < menuCount.size(); i++) {
            if (menuCount.get(i) < 1) {
                throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
            }
        }
    }

    private static void validateCounts(List<Integer> menuCount) {
        int totalCount = menuCount.stream().mapToInt(Integer::intValue).sum();
        if (totalCount > 20) {
            throw new IllegalArgumentException(NOT_OVER_TWENTY.getMessage());
        }
    }

    private static void validateOnlyDrink(List<String> menuNames) {
        if (ifOnlyDrink(menuNames)) {
            throw new IllegalArgumentException(NOT_ONLY_DRINK.getMessage());
        }
    }

    private static boolean ifOnlyDrink(List<String> menuNames) { //클래스 분리 고려(menuNames)
        return menuNames.stream()
                .map(Menu::of)
                .allMatch(menu -> menu.getType().equals("음료"));
    }
}
