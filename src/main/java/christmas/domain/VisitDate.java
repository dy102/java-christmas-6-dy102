package christmas.domain;

import static christmas.option.Error.ILLEGAL_DATE;

public record VisitDate(int date) {
    public VisitDate {
        validate(date);
    }

    private void validate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException(ILLEGAL_DATE.getMessage());
        }
    }
}
