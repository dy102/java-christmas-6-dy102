package christmas.option;

import java.util.Arrays;

import static christmas.option.Error.ILLEGAL_ORDER;

public enum Menu {
    APPETIZER_1("에피타이저", "양송이수프", 6000),
    APPETIZER_2("에피타이저", "타파스", 5500),
    APPETIZER_3("에피타이저", "시저샐러드", 8000),
    MAIN_1("메인", "티본스테이크", 55000),
    MAIN_2("메인", "바비큐립", 54000),
    MAIN_3("메인", "해산물파스타", 35000),
    MAIN_4("메인", "크리스마스파스타", 25000),
    DESSERT_1("디저트", "초코케이크", 15000),
    DESSERT_2("디저트", "아이스크림", 5000),
    DRINK_1("음료", "제로콜라", 3000),
    DRINK_2("음료", "레드와인", 60000),
    DRINK_3("음료", "샴페인", 25000);

    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public static Menu of(String name) {
        return Arrays.stream(values())
                .filter(v -> name.equals(v.name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ILLEGAL_ORDER.getMessage()));
    }
}
