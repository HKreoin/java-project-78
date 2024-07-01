package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema schema;
    private Validator validator;
    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void testRequired() {
        assertFalse(schema.getRequired());
        schema.required();
        assertTrue(schema.getRequired());
    }

    @Test
    public void testMinLength() {
        assertEquals(schema.getMinLength(), 0);
        schema.minLength(5);
        assertEquals(schema.getMinLength(), 5);
    }

    @Test
    public void contains() {
        assertTrue(schema.getContains().isEmpty());
        schema.contains("hexlet");
        assertEquals(schema.getContains().get(0), "hexlet");
        schema.contains("what");
        assertEquals(schema.getContains().get(1), "what");
    }

    @Test
    public void testIsValid() {

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));

        var schema1 = validator.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("Hexlet"));
    }
}
