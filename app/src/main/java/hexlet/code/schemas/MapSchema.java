package hexlet.code.schemas;

import hexlet.code.checks.RequiredMapCheck;
import hexlet.code.checks.SizeOfCheck;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        checks.add(new RequiredMapCheck());
        return this;
    }
    public MapSchema sizeOf(int size) {
        checks.add(new SizeOfCheck(size));
        return  this;
    }
}
