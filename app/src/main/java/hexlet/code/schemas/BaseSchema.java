package hexlet.code.schemas;

import hexlet.code.checks.Check;
import hexlet.code.checks.RequaredCheck;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BaseSchema<T> {
    protected List<Check> checks;
    public BaseSchema() {
        this.checks = new ArrayList<>();
    }

    public BaseSchema<T> required() {
        checks.add(new RequaredCheck());
        return this;
    }

    public boolean isValid(T content) {
        for (var check : checks) {
            if (!check.validate(content)) {
                return false;
            }
        }
        return true;
    }

}
