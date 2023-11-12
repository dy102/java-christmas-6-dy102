package christmas.domain;

public record VisitDate(int date) {
    public VisitDate {
        validate(date);
    }

    private void validate(int visitDate) {
        if (visitDate < 1 || visitDate > 31) {
            throw new IllegalArgumentException("");
        }
    }
}
