package hexlet.code.checks;

import java.util.Map;

public class SizeOfCheck<T> implements Check<Map<String, T>> {
    private final int size;

    public SizeOfCheck(int size) {
        this.size = size;
    }

    @Override
    public boolean validate(Map<String, T> content) {
        return content != null && content.size() == size;
    }
}
