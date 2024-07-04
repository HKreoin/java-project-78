package hexlet.code.checks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MinLengthCheck implements Check<String> {
    private int minLength;

    public MinLengthCheck(int min) {
        this.minLength = min;
    }

    @Override
    public boolean validate(String content) {
        return content.length() >= minLength;
    }

}
