package hexlet.code.checks;

public final class RangeCheck implements Check<Integer> {
    private final int from;
    private final int to;

    public RangeCheck(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean validate(Integer content) {
        return content >= from && content <= to;
    }
}
