package hexlet.code.schemas;

import lombok.NoArgsConstructor;



@NoArgsConstructor
public abstract class Schema {
    private boolean required;

    public Schema required() {
        required = true;
        return this;
    }
    public boolean getRequired() {
        return required;
    }
    public abstract boolean isValid(String str);
}
