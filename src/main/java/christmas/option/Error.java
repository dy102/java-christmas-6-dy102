package christmas.option;

import static christmas.option.EventOption.MAXIMUM_BOUND_COUNT;

public enum Error {
    ILLEGAL_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ILLEGAL_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_ONLY_DRINK("음료만 주문할 수 없습니다."),
    NOT_OVER_MAXIMUM_COUNT("메뉴는 한 번에 최대 " + MAXIMUM_BOUND_COUNT + "개까지만 주문할 수 있습니다.");
    private final String message;

    private Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
