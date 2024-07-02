package hexlet.code.schemas;

import hexlet.code.checks.ContainsCheck;
import hexlet.code.checks.MinLengthCheck;

public class StringSchema extends BaseSchema<String> {
    private MinLengthCheck minLengthCheck;

    public StringSchema minLength(int min) {
        if (min > 0) {
            if (minLengthCheck == null) {
                minLengthCheck = new MinLengthCheck(min);
            } else {
                minLengthCheck.setMinLength(min);
            }
            checks.add(minLengthCheck);
        }
        return this;
    }
    public StringSchema contains(String subStr) {
        checks.add(new ContainsCheck(subStr));
        return this;
    }

}
