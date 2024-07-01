package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorTest {
    @Test
    public void testString(){
        var validator = new Validator();
        var expected = StringSchema.class;
        assertEquals(validator.string().getClass(), expected);
    }
}
