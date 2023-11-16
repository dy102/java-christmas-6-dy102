package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.util.Converter;

import java.util.List;

public class Input {
    private Input() {
    }

    public static int visitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Converter.stringToInt(Console.readLine());
    }

    public static List<String> userMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return Converter.stringToList(Console.readLine());
    }
}
