package christmas.domain.user;

import static christmas.option.Error.ILLEGAL_DATE;
import static christmas.option.EventOption.END_DATE;
import static christmas.option.EventOption.START_DATE;

public record VisitDate(int date) {
    public VisitDate {
        validate(date);
    }

    private void validate(int visitDate) {
        if (visitDate < START_DATE || visitDate > END_DATE) {
            throw new IllegalArgumentException(ILLEGAL_DATE.getMessage());
        }
    }
}
