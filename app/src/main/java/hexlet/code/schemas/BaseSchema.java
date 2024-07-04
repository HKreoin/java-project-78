package hexlet.code.schemas;

import hexlet.code.checks.Check;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class BaseSchema<T> {
    protected List<Check<T>> checks;
    public BaseSchema() {
        this.checks = new ArrayList<>();
    }

    public boolean isValid(T content) {
        for (Check<T> check : checks) {
            if (!check.validate(content)) {
                return false;
            }
        }
        return true;
    }

}
