package christmas.domain.benefit;

public class Badge {
    private String name = "없음";

    public String getName() {
        return name;
    }

    public void setName(int totalServicePrice) {
        if (totalServicePrice <= -20000) {
            name = "산타";
            return;
        }
        if (totalServicePrice <= -10000) {
            name = "트리";
            return;
        }
        if (totalServicePrice <= -5000) {
            name = "별";
        }
    }
}
