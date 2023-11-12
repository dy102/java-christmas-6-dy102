package christmas.view;

import java.util.Arrays;
import java.util.List;

import static christmas.option.Error.ILLEGAL_DATE;

public class Converter {
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
        return Arrays.stream(order.split(",")).toList();
    }
}
