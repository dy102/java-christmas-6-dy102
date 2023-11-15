package christmas.option;

import christmas.domain.event.DDayEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiveAwayEvent;
import christmas.domain.event.SpecialEvent;
import christmas.domain.event.WeekdayEvent;
import christmas.domain.event.WeekendEvent;

import java.util.Arrays;

public enum EventBundle {
    WEEKDAY_EVENT("평일 할인", new WeekdayEvent()),
    WEEKEND_EVENT("주말 할인", new WeekendEvent()),
    D_DAY_EVENT("크리스마스 디데이 할인", new DDayEvent()),
    SPECIAL_EVENT("특별 할인", new SpecialEvent()),
    GIVE_AWAY_EVENT("증정 이벤트", new GiveAwayEvent());

    private final String name;
    private final Event event;

    private EventBundle(String name, Event event) {
        this.name = name;
        this.event = event;
    }

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }

    public static EventBundle of(Event event) {
        return Arrays.stream(values())
                .filter(v -> event.getClass() == v.event.getClass())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("존재하지 않는 이벤트 클래스입니다."));
    }
}