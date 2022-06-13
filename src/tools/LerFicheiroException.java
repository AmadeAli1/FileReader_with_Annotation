package tools;

public class LerFicheiroException extends Exception{
    public LerFicheiroException() {
        this("Verifique as anotacoes");
    }

    public LerFicheiroException(String message) {
        super(message);
    }
}
