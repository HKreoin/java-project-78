package hexlet.code.checks;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinLengthCheck implements Check<String> {
    private int minLength;

    public MinLengthCheck(int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String content) {
        return content.length() >= minLength;
    }

}
