package tools;

public class JsonException extends Exception {

    public JsonException() {
        this("Falha ao convertes");
    }

    public JsonException(String message) {
        super(message);
    }
}
