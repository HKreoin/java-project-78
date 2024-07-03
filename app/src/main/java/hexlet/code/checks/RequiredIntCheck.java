package hexlet.code.checks;

public class RequiredIntCheck implements Check<Integer> {
    @Override
    public boolean validate(Integer content) {
        return content != null;
    }
}
