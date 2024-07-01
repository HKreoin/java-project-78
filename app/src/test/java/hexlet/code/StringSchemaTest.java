package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSchemaTest {
    private Schema schema;
    private Validator validator;
    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.string();
    }

    @Test
    public void testRequired() {
        assertEquals(schema.getRequired(), false);
        schema.required();
        assertEquals(schema.getRequired(), true);
    }

    @Test
    public void testMinLength() {
        assertEquals(schema.getMinLength(), 0);
        schema.minLength(5);
        assertEquals(schema.getMinLength(), 5);
    }

    @Test
    public void contains() {
        assertEquals(schema.getContains().isEmpty(), true);
        schema.contains("hexlet");
        assertEquals(schema.getContains().get(0), "hexlet");
        schema.contains("what");
        assertEquals(schema.getContains().get(1), "what");
    }

    @Test
    public void testIsValid() {

        assertEquals(schema.isValid(""), true);
        assertEquals(schema.isValid(null), true);

        schema.required();

        assertEquals(schema.isValid(null), false);
        assertEquals(schema.isValid(""), false);
        assertEquals(schema.isValid("what does the fox say"), true);
        assertEquals(schema.isValid("hexlet"), true);

        assertEquals(schema.contains("wh").isValid("what does the fox say"), true);
        assertEquals(schema.contains("what").isValid("what does the fox say"), true);
        assertEquals(schema.contains("whatthe").isValid("what does the fox say"), false);

        assertEquals(schema.isValid("what does the fox say"), false);

        var schema1 = validator.string();
        assertEquals(schema1.minLength(10).minLength(4).isValid("Hexlet"), true);
    }
}
