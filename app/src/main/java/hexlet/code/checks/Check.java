package hexlet.code.checks;

public interface Check<T> {
    boolean validate(T content);
}
