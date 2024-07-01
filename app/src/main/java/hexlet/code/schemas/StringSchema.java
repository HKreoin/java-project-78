package hexlet.code.schemas;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StringSchema extends Schema{
    private List<String> contains = new ArrayList<>();
    private int minLength;
    public StringSchema minLength(int min) {
        if (min > 0) minLength = min;
        return this;
    }
    public StringSchema contains(String subStr) {
        contains.add(subStr);
        return this;
    }
    @Override
    public boolean isValid(String str) {
        if (getRequired() && str == null) {
            return false;
        }
        if (getRequired() && str.isEmpty()) {
            return false;
        }
        if (minLength > 0 && str.length() < minLength) {
            return false;
        }
        return allContentInclude(str);
    }

    public boolean allContentInclude(String str) {
        if (contains.isEmpty()) {
            return true;
        }
        for (var substring : contains) {
            if (!str.contains(substring)) {
                return false;
            }
        }
        return true;
    }
}
