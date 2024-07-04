package hexlet.code.checks;

import lombok.Getter;

@Getter
public final class ContainsCheck implements Check<String> {
    private final String subStr;
    public ContainsCheck(String sub) {
        this.subStr = sub;
    }

    @Override
    public boolean validate(String content) {
        return content.contains(subStr);
    }
}
