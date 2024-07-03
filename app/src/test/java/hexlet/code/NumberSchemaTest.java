package hexlet.code;

import hexlet.code.checks.MinLengthCheck;
import hexlet.code.checks.RequaredCheck;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberSchemaTest {
    private StringSchema schema;
    private Validator validator;

    @BeforeEach
    public void beforeEach() {
        validator = new Validator();
        schema = validator.number();
    }

    @Test
    public void testRequired() {
        assertTrue(schema.getChecks().isEmpty());
        schema.required();
        assertEquals(schema.getChecks().getFirst().getClass(), RequaredCheck.class);

    }

    @Test
    public void testPositive() {
        assertTrue(schema.getChecks().isEmpty());
        schema.positive();
        PositiveCheck check = (PositiveCheck) schema.getChecks().getFirst();
        assertEquals(check, PositiveCheck.class);
        assertTrue(check.validate(null));
        assertTrue(check.validate(34));
        assertFalse(check.validate(-4));
        assertFalse(check.validate(0));
    }

    @Test
    public void testRange() {
        assertTrue(schema.getChecks().isEmpty());
        schema.range(5, 10);
        RangeCheck check = (RangeCheck) schema.getChecks().getFirst();
        assertTrue(check.validate(7));
        assertTrue(check.validate(10));
        assertFalse(check.validate(4));
    }

    @Test
    public void testIsValid() {

        assertTrue(schema.isValid(5)); // true

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true

        // Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }
}
