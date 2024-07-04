package hexlet.code.checks;

import lombok.Getter;

@Getter
public final class ContainsCheck implements Check<String> {
    private final String subStr;
    public ContainsCheck(String subStr) {
        this.subStr = subStr;
    }

    @Override
    public boolean validate(String content) {
        return content.contains(subStr);
    }
}
