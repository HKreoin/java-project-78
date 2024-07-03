package hexlet.code.checks;

public class RequaredIntCheck implements Check<Integer> {
    @Override
    public boolean validate(Integer content) {
        return content != null;
    }
}
