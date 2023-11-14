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

        VisitDate visitDate = inputVisitDate();
        UserMenu userMenu = inputUserMenu();
        TotalPrice totalPrice = getTotalPrice(userMenu);

        EventParameter eventParameter = new EventParameter(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(eventParameter);
        applyTotalEvent(eventPrice);

        Badge badge = new Badge();
        badge.setName(eventPrice.getTotalEventPrice());

        printEventPreview(eventParameter, eventPrice, badge);
    }

    private VisitDate inputVisitDate() {
        while (true) {
            try {
                return new VisitDate(Input.visitDate());
            } catch (IllegalArgumentException e) {
                Output.error(e);
            }
        }
    }

    private UserMenu inputUserMenu() {
        while (true) {
            try {
                return UserMenu.form(Input.userMenu());
            } catch (IllegalArgumentException e) {
                Output.error(e);
            }
        }
    }

    private TotalPrice getTotalPrice(UserMenu userMenu) {
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames(), userMenu.getMenuCount());
        return totalPrice;
    }

    private void applyTotalEvent(EventPrice eventPrice) {
        EventManager eventManager = new EventManager(eventPrice);
        eventManager.collectAllEvent();
    }

    private void printEventPreview(EventParameter eventParameter, EventPrice eventPrice, Badge badge) {
        int totalPriceBeforeDiscount = eventParameter.totalPrice().price();
        int totalPriceAfterDiscount = eventParameter.totalPrice().applyDiscount(eventPrice.getDiscountPrice());

        printIntroduction(eventParameter.visitDate());
        printOrderMenu(eventParameter.userMenu());
        printTotalPriceBeforeDisCount(totalPriceBeforeDiscount);
        printGiveMenu(eventPrice.getAppliedEvent());
        printAppliedEvents(eventPrice.getAppliedEvent(), eventPrice.getAppliedPrice());
        printTotalEventPrice(eventPrice.getTotalEventPrice());
        printTotalPriceAfterDiscount(totalPriceAfterDiscount);
        printBadge(badge);
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

    private void printGiveMenu(List<String> appliedEvent) {
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
