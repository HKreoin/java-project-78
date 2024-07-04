package hexlet.code;

import hexlet.code.checks.RequiredCheck;
import hexlet.code.checks.SizeOfCheck;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

        schema.required();

        var check = (RequiredCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), RequiredCheck.class);
        assertFalse(check.validate(null));

        var data = new HashMap<>();
        assertTrue(check.validate(data));
        data.put("key1", "value1");
        assertTrue(check.validate(data));
    }

    @Test
    public void testSizeof() {

        schema.sizeof(2);

        var check = (SizeOfCheck) schema.getChecks().getFirst();
        assertEquals(check.getClass(), SizeOfCheck.class);
        assertFalse(check.validate(null));

        var data = new HashMap<String, String>();
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

    @Test
    public void testShape() {
        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        // Создаем набор схем для проверки каждого ключа проверяемого объекта
        // Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        // Определяем схемы валидации для значений свойств "firstName" и "lastName"
        // Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", validator.string().required());
        // Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", validator.string().required().minLength(2));

        // Настраиваем схему `MapSchema`
        // Передаем созданный набор схем в метод shape()
        schema.shape(schemas);

        // Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false
    }
}
