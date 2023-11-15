package christmas.view;

import java.text.DecimalFormat;
import java.util.List;

import static christmas.option.EventName.GIVE_AWAY_EVENT;
import static christmas.option.EventOption.EVENT_MONTH;

public class Output {
    public static final String ORDER_MENU = "<주문 메뉴>";
    public static final String TOTAL_PRICE_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    public static final String GIVE_AWAY_MENU = "<증정 메뉴>";
    public static final String APPLIED_EVENT = "<혜택 내역>";
    public static final String TOTAL_EVENT_PRICE = "<총혜택 금액>";
    public static final String TOTAL_PRICE_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    public static final String BADGE = "<" + EVENT_MONTH + "월 이벤트 배지>";

    private static final DecimalFormat format = new DecimalFormat("###,###,###,###");

    private Output() {
    }

    public static void message(String message) {
        System.out.println(message);
    }

    public static void error(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void intro() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void eventIntroduction(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
        lineBreak();
    }

    public static void orderMenu(List<String> menuNames, List<Integer> menuCount) {
        for (int i = 0; i < menuNames.size(); i++) {
            System.out.println(menuNames.get(i) + " " + menuCount.get(i) + "개");
        }
        lineBreak();
    }

    public static void price(int price) {
        System.out.println(format.format(price) + "원");
        lineBreak();
    }

    public static void giveMenu(List<String> appliedEvent) {
        if (appliedEvent.contains(GIVE_AWAY_EVENT.getName())) { //상수관리필요
            System.out.println("샴페인 1개");
            lineBreak();
            return;
        }
        System.out.println("없음");
        lineBreak();
    }

    public static void appliedEvents(List<String> appliedEvent, List<Integer> appliedPrice) {
        if (appliedEvent.isEmpty()) {
            System.out.println("없음");
            lineBreak();
            return;
        }
        for (int i = 0; i < appliedEvent.size(); i++) {
            System.out.print(appliedEvent.get(i) + ": ");
            System.out.println(format.format(appliedPrice.get(i)) + "원");
        }
        lineBreak();
    }

    private static void lineBreak() {
        System.out.println();
    }
}
