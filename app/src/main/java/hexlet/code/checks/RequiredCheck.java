package hexlet.code.checks;

public class RequiredCheck<T> implements Check<T> {
    @Override
    public boolean validate(T content) {
        if (content == "") {
            return false;
        }
        return content != null;
    }

}
