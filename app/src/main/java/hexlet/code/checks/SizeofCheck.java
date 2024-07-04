package hexlet.code.checks;

import java.util.Map;

public final class SizeofCheck<T> implements Check<Map<String, T>> {
    private final int size;

    public SizeofCheck(int mapSize) {
        this.size = mapSize;
    }

    @Override
    public boolean validate(Map<String, T> content) {
        return content != null && content.size() == size;
    }
}
