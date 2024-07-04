package hexlet.code;

import hexlet.code.checks.ContainsCheck;
import hexlet.code.checks.MinLengthCheck;
import hexlet.code.checks.RequiredCheck;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private StringSchema schema;
    private final Validator validator  = new Validator();

    @BeforeEach
    public void beforeEach() {
        schema = validator.string();
    }

    @Test
    public void testRequired() {
        schema.required();
        assertEquals(schema.getChecks().getFirst().getClass(), RequiredCheck.class);
    }

    @Test
    public void testMinLength() {
        schema.minLength(5);
        MinLengthCheck check = (MinLengthCheck) schema.getChecks().getFirst();
        assertEquals(check.getMinLength(), 5);
    }

    @Test
    public void contains() {
        schema.contains("hexlet");
        var expected1 = (ContainsCheck) schema.getChecks().getFirst();
        assertEquals(expected1.getSubStr(), "hexlet");
        schema.contains("what");
        var expected2 = (ContainsCheck) schema.getChecks().get(1);
        assertEquals(expected2.getSubStr(), "what");
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
