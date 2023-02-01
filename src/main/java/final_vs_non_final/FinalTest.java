package final_vs_non_final;

public class FinalTest {
    private final String finalField;
    private String nonFinalField;

    public FinalTest(final String finalField) {
        this.finalField = finalField;
    }
}
