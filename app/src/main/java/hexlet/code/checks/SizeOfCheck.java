package hexlet.code.checks;

import java.util.Map;

public class SizeOfCheck implements Check<Map<?, ?>> {
    private final int size;

    public SizeOfCheck(int size) {
        this.size = size;
    }

    @Override
    public boolean validate(Map<?, ?> content) {
        return content != null && content.size() == size;
    }
}
