package anotation;

public enum DataType {
    BOOLEAN {
        @Override
        public Object convert(String value) {
            return Boolean.parseBoolean(value);
        }
    },
    BYTE {
        @Override
        public Object convert(String value) {
            return Byte.parseByte(value);
        }
    },
    DOUBLE {
        @Override
        public Object convert(String value) {
            return Double.parseDouble(value);
        }
    },
    FLOAT {
        @Override
        public Object convert(String value) {
            return Float.parseFloat(value);
        }
    },
    INTEGER {
        @Override
        public Object convert(String value) {
            return Integer.parseInt(value);
        }
    },
    LONG {
        @Override
        public Object convert(String value) {
            return Long.parseLong(value);
        }
    },
    SHORT {
        @Override
        public Object convert(String value) {
            return Short.parseShort(value);
        }
    },
    STRING {
        @Override
        public Object convert(String value) {
            return value;
        }
    };


    abstract public Object convert(String value);

//    public static Object convert(DataType type, String valor) {
//        return switch (type) {
//            case BYTE -> Byte.parseByte(valor.trim());
//            case FLOAT -> Float.parseFloat(valor.trim());
//            case SHORT -> Short.parseShort(valor.trim());
//            case DOUBLE -> Double.parseDouble(valor.trim());
//            case STRING -> valor;
//            case INTEGER -> Integer.parseInt(valor.trim());
//            case BOOLEAN -> Boolean.parseBoolean(valor.trim());
//            case LONG -> Long.parseLong(valor.trim());
//        };
//    }

}
