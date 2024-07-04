package hexlet.code.checks;

import hexlet.code.schemas.BaseSchema;

import java.util.Map;

public class ShapeCheck <T> implements Check <Map<String,T> > {

    private final Map<String, BaseSchema<T>> schemas;
    public ShapeCheck(Map<String, BaseSchema<T>> schemas) {
        this.schemas = schemas;
    }

    @Override
    public boolean validate(Map<String,T> content) {
        for (var entry : content.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            if (!schemas.get(key).isValid(value)) {
                return false;
            }
        }
        return true;
    }

}
