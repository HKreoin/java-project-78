package hexlet.code;

import hexlet.code.checks.RequaredIntCheck;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private MapSchema schema;
    private final Validator validator = new Validator();

    @BeforeEach
    public void beforeEach() {
        schema = validator.map();
    }

    @Test
    public void testRequired() {
        assertTrue(schema.getChecks().isEmpty());
        schema.required();
        var check = (RequaredMapCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), RequaredMapCheck.class);
        assertFalse(check.validate(null));
        var data = new HashMap<>();
        assertTrue(check.validate(data));
        data.put("key1", "value1");
        assertTrue(check.validate(data));
    }

    @Test
    public void testSizeOf() {
        assertTrue(schema.getChecks().isEmpty());
        schema.sizeOf(2);
        var check = (SizeOfMapCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), SizeOfCheck.class);
        assertFalse(check.validate(null));
        var data = new HashMap<>();
        assertFalse(check.validate(data));
        data.put("key1", "value1");
        assertFalse(check.validate(data));
        data.put("key2", "value2");
        assertTrue(check.validate(data));
        data.put("key3", "value3");
        assertFalse(check.validate(data));
    }

    @Test
    public void testIsValid() {
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }
}
