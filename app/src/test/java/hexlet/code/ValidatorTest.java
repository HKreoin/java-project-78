package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
    private Validator validator = new Validator();

    @Test
    public void testString() {
        var expected = StringSchema.class;
        assertEquals(validator.string().getClass(), expected);
    }

    @Test
    public void testNumber() {
        var expected = NumberSchema.class;
        assertEquals(validator.number().getClass(), expected);
    }

    @Test
    public void testMap() {
        var expected = MapSchema.class;
        assertEquals(validator.map().getClass(), expected);
    }
}
