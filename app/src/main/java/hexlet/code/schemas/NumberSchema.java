package hexlet.code.schemas;

import hexlet.code.checks.PositiveCheck;
import hexlet.code.checks.RangeCheck;
import hexlet.code.checks.RequaredIntCheck;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        checks.add(new PositiveCheck());
        return this;
    }

    public NumberSchema required() {
        checks.add(new RequaredIntCheck());
        return this;
    }

    public NumberSchema range(int from, int to) {
        checks.add(new RangeCheck(from, to));
        return this;
    }
}