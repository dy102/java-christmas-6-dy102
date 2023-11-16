package christmas.domain.user;

import java.util.List;

import static christmas.option.Error.ILLEGAL_ORDER;
import static christmas.option.Error.NOT_OVER_MAXIMUM_COUNT;
import static christmas.option.EventOption.MAXIMUM_BOUND_COUNT;
import static christmas.option.EventOption.MINIMUM_BOUND_COUNT;

public record MenuCounts(List<Integer> counts) {
    public MenuCounts {
        validate(counts);
    }

    public void validate(List<Integer> menuCount) {
        validateCount(menuCount);
        validateCounts(menuCount);
    }

    private void validateCount(List<Integer> menuCount) {
        for (int i = 0; i < menuCount.size(); i++) {
            if (menuCount.get(i) < MINIMUM_BOUND_COUNT.getNumber()) {
                throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
            }
        }
    }

    private static void validateCounts(List<Integer> menuCount) {
        int totalCount = menuCount.stream().mapToInt(Integer::intValue).sum();
        if (totalCount > MAXIMUM_BOUND_COUNT.getNumber()) {
            throw new IllegalArgumentException(NOT_OVER_MAXIMUM_COUNT.getMessage());
        }
    }

    public int get(int index) {
        return counts.get(index);
    }
}
