package anotation;

public enum DataType {
    BOOLEAN,
    BYTE,
    DOUBLE,
    FLOAT,
    INTEGER,
    LONG,
    SHORT,
    STRING;

    public static Object convert(DataType type, String valor) {
        return switch (type) {
            case BYTE -> Byte.parseByte(valor.trim());
            case FLOAT -> Float.parseFloat(valor.trim());
            case SHORT -> Short.parseShort(valor.trim());
            case DOUBLE -> Double.parseDouble(valor.trim());
            case STRING -> valor;
            case INTEGER -> Integer.parseInt(valor.trim());
            case BOOLEAN -> Boolean.parseBoolean(valor.trim());
            case LONG -> Long.parseLong(valor.trim());
        };
    }

}
