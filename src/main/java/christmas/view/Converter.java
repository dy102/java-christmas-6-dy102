package christmas.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static christmas.option.Error.ILLEGAL_DATE;
import static christmas.option.Error.ILLEGAL_ORDER;

public class Converter {
    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_DASH = "-";

    private Converter() {
    }

    public static int stringToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_DATE.getMessage());
        }
    }

    public static List<String> stringToList(String order) {
        return Arrays.stream(order.split(DELIMITER_COMMA)).toList();
    }

    public static List<String> orderToMenuNames(List<String> orders) {
        List<String> menuNames = new ArrayList<>();
        for (String order : orders) {
            Arrays.stream(order.split(DELIMITER_DASH)).findFirst().ifPresent(menuNames::add);
        }
        return menuNames;
    }

    public static List<Integer> orderToMenuCount(List<String> orders) {
        List<Integer> menuCount = new ArrayList<>();
        for (String order : orders) {
            List<String> splitOrder = Arrays.stream(order.split(DELIMITER_DASH)).toList();
            try {
                menuCount.add(Integer.parseInt(splitOrder.get(1)));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(ILLEGAL_ORDER.getMessage());
            }
        }
        return menuCount;
    }
}
