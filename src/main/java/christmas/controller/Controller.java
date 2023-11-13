package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.EventManager;
import christmas.domain.EventParameter;
import christmas.domain.EventPrice;
import christmas.domain.TotalPrice;
import christmas.domain.UserMenu;
import christmas.domain.VisitDate;
import christmas.option.EventName;
import christmas.view.Input;
import christmas.view.Output;

import java.util.List;

public class Controller {
    public void run() {
        Output.intro();

        VisitDate visitDate = setVisitDate();
        UserMenu userMenu = setUserMenu();
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(eventParameter);
        EventManager eventManager = new EventManager(eventPrice);
        eventManager.collectAllEvent(totalPrice);

        List<String> appliedEvent = eventPrice.getAppliedEvent();
        List<Integer> appliedPrice = eventPrice.getAppliedPrice();

        int totalPriceBeforeDiscount = totalPrice.price();
        int totalEventService = eventPrice.getTotalEventPrice();
        int totalPriceAfterDiscount = totalPrice.applyDiscount(eventPrice.getDiscountPrice());

        Badge badge = new Badge();
        badge.setName(totalEventService);
//print용 DTO같은 걸 만들거나. 매개변수를 좀 다르게 해보던가.
        printIntroduction(visitDate);

        printOrderMenu(userMenu);

        printTotalPriceBeforeDisCount(totalPriceBeforeDiscount);

        printGiveMenu(appliedEvent, totalPriceBeforeDiscount);

        printAppliedEvents(appliedEvent, appliedPrice);

        printTotalEventPrice(totalEventService);

        printTotalPriceAfterDiscount(totalPriceAfterDiscount);

        printBadge(badge);
    }

    private VisitDate setVisitDate() {
        while (true) {
            try {
                return new VisitDate(Input.visitDate());
            } catch (IllegalArgumentException e) {
                Output.error(e);
            }
        }
    }

    private UserMenu setUserMenu() {
        while (true) {
            try {
                return UserMenu.form(Input.userMenu());
            } catch (IllegalArgumentException e) {
                Output.error(e);
            }
        }
    }

    private void printIntroduction(VisitDate visitDate) {
        Output.eventIntroduction(visitDate.date());
    }

    private void printOrderMenu(UserMenu userMenu) {
        Output.message(Output.ORDER_MENU);
        Output.orderMenu(userMenu.getMenuNames(), userMenu.getMenuCount());
    }

    private void printTotalPriceBeforeDisCount(int price) {
        Output.message(Output.TOTAL_PRICE_BEFORE_DISCOUNT);
        Output.price(price);
    }

    private void printGiveMenu(List<String> appliedEvent, int price) {
        Output.message(Output.GIVE_AWAY_MENU);
        Output.giveMenu(appliedEvent);
    }

    private void printAppliedEvents(List<String> appliedEvent, List<Integer> appliedPrice) {
        Output.message(Output.APPLIED_EVENT);

        if (appliedEvent.isEmpty()) {
            Output.message("없음");
            Output.lineBreak();
            return;
        }

        EventName[] events = EventName.values();
        for (EventName event : events) {
            Output.appliedEvent(event, appliedEvent, appliedPrice);
        }
        Output.lineBreak();
    }

    private void printTotalEventPrice(int totalServicePrice) {
        Output.message(Output.TOTAL_EVENT_PRICE);
        Output.price(totalServicePrice);
    }

    private void printTotalPriceAfterDiscount(int totalPriceAfterDiscount) {
        Output.message(Output.TOTAL_PRICE_AFTER_DISCOUNT);
        Output.price(totalPriceAfterDiscount);
    }

    private void printBadge(Badge badge) {
        Output.message(Output.BADGE);
        Output.message(badge.getName());
    }
}
