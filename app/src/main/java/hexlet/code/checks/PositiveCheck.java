package hexlet.code.checks;

public class PositiveCheck implements Check<Integer> {
    @Override
    public boolean validate(Integer content) {
        return content == null || content > 0;
    }
}
