package christmas.controller;

import christmas.domain.benefit.Badge;
import christmas.domain.benefit.EventManager;
import christmas.domain.benefit.EventPrice;
import christmas.domain.user.TotalPrice;
import christmas.domain.user.UserInformation;
import christmas.domain.user.UserMenu;
import christmas.domain.user.VisitDate;
import christmas.view.Input;
import christmas.view.Output;

import java.util.List;

import static christmas.view.Output.APPLIED_EVENT;
import static christmas.view.Output.GIVE_AWAY_MENU;
import static christmas.view.Output.ORDER_MENU;
import static christmas.view.Output.TOTAL_PRICE_BEFORE_DISCOUNT;

public class Controller {
    public void run() {
        Output.intro();

        VisitDate visitDate = inputVisitDate();
        UserMenu userMenu = inputUserMenu();
        TotalPrice totalPrice = getTotalPrice(userMenu);

        UserInformation userInformation = new UserInformation(visitDate, userMenu, totalPrice);
        EventPrice eventPrice = new EventPrice(userInformation);
        applyTotalEvent(eventPrice);

        Badge badge = new Badge();
        badge.setName(eventPrice.getTotalEventPrice());

        printEventPreview(userInformation, eventPrice, badge);
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
                return UserMenu.from(Input.userMenu());
            } catch (IllegalArgumentException e) {
                Output.error(e);
            }
        }
    }

    private TotalPrice getTotalPrice(UserMenu userMenu) {
        TotalPrice totalPrice = new TotalPrice();
        totalPrice.caculateTotalPrice(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());
        return totalPrice;
    }

    private void applyTotalEvent(EventPrice eventPrice) {
        EventManager eventManager = new EventManager(eventPrice);
        eventManager.collectAllEvent();
    }

    private void printEventPreview(UserInformation userInformation, EventPrice eventPrice, Badge badge) {
        int totalPriceBeforeDiscount = userInformation.totalPrice().price();
        int totalPriceAfterDiscount = userInformation.totalPrice().applyDiscount(eventPrice.getDiscountPrice());

        printIntroduction(userInformation.visitDate());
        printOrderMenu(userInformation.userMenu());
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
        Output.message(ORDER_MENU);
        Output.orderMenu(userMenu.getMenuNames().names(), userMenu.getMenuCounts().counts());
    }

    private void printTotalPriceBeforeDisCount(int price) {
        Output.message(TOTAL_PRICE_BEFORE_DISCOUNT);
        Output.price(price);
    }

    private void printGiveMenu(List<String> appliedEvent) {
        System.out.println(GIVE_AWAY_MENU);
        Output.giveMenu(appliedEvent);
    }

    private void printAppliedEvents(List<String> appliedEvent, List<Integer> appliedPrice) {
        Output.message(APPLIED_EVENT);
        Output.appliedEvents(appliedEvent, appliedPrice);
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
