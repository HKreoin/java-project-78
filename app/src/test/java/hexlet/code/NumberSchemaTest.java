package hexlet.code;

import hexlet.code.checks.PositiveCheck;
import hexlet.code.checks.RangeCheck;
import hexlet.code.checks.RequiredCheck;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberSchemaTest {
    private NumberSchema schema;
    private final Validator validator = new Validator();

    @BeforeEach
    public void beforeEach() {
        schema = validator.number();
    }

    @Test
    public void testRequired() {
        schema.required();
        var check = (RequiredCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), RequiredCheck.class);
        assertFalse(check.validate(null));
        assertTrue(check.validate(34));
        assertTrue(check.validate(-4));
        assertTrue(check.validate(0));
    }

    @Test
    public void testPositive() {
        schema.positive();
        var check = (PositiveCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), PositiveCheck.class);
        assertTrue(check.validate(null));
        assertTrue(check.validate(34));
        assertFalse(check.validate(-4));
        assertFalse(check.validate(0));
    }

    @Test
    public void testRange() {
        schema.range(5, 10);
        var check = (RangeCheck) schema.getChecks().getFirst();
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
