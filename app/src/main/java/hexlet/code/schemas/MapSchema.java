package hexlet.code.schemas;

import hexlet.code.checks.RequiredCheck;
import hexlet.code.checks.ShapeCheck;
import hexlet.code.checks.SizeOfCheck;

import java.util.Map;

public class MapSchema<T> extends BaseSchema<Map<String, T>> {

    public MapSchema<T> sizeOf(int size) {
        checks.add(new SizeOfCheck<T>(size));
        return  this;
    }

    public MapSchema<T> required() {
        checks.add(new RequiredCheck<>());
        return this;
    }

    public MapSchema<T> shape(Map<String,BaseSchema<T>> shapes) {
        checks.add(new ShapeCheck<T>(shapes));
        return this;
    }
}
