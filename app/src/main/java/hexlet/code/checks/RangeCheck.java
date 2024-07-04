package hexlet.code.checks;

public final class RangeCheck implements Check<Integer> {
    private final int f;
    private final int t;

    public RangeCheck(int from, int to) {
        this.f = from;
        this.t = to;
    }

    @Override
    public boolean validate(Integer content) {
        return content >= f && content <= t;
    }
}
