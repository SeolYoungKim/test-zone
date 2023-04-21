package dip.after;

public class CalculateDiscountService {
    private final RuleEngine ruleEngine;  // 인터페이스에 의존 -> 구현체에 영향을 받지 않음

    public CalculateDiscountService(final RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public void doSomething() {
        // 대충 긴 로직

        ruleEngine.evaluate("discountCalculation");  // 인터페이스에 의존하므로, 구현체가 뭐가 오든지간에 인터페이스가 변경되지 않으면 변경의 영향을 받지 않음

        // 대충 엄청 긴 로직
    }
}
