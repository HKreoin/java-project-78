package hexlet.code.checks;

import java.util.Map;

public class RequiredMapCheck implements Check<Map<?, ?>> {

    @Override
    public boolean validate(Map<?, ?> content) {
        return content != null;
    }
}
