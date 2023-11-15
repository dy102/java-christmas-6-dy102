package christmas.domain.user;

import christmas.option.Menu;

import java.util.List;

public class TotalPrice {
    private int price = 0;

    public void caculateTotalPrice(List<String> menuNames, List<Integer> menuCount) {
        for (int i = 0; i < menuNames.size(); i++) {
            Menu name = Menu.of(menuNames.get(i));
            int count = menuCount.get(i);
            price += name.getPrice() * count;
        }
    }

    public int applyDiscount(int totalDiscount) {
        price += totalDiscount;
        return price;
    }

    public int price() {
        return price;
    }
}
