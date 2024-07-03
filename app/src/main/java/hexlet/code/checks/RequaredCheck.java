package hexlet.code.checks;

public class RequaredCheck implements Check<String> {
    @Override
    public boolean validate(String content) {
        if (content == null) {
            return false;
        } else {
            return !content.isEmpty();
        }
    }

}
