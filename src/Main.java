import model.Person;
import tools.JsonException;
import tools.ToJson;

public class Main {

    public static void main(String[] args) throws JsonException {
        var xs = new ToJson();
        var ps = new Person("amade","ali");

        String convert = xs.convert(ps);
        System.out.println(convert);
    }
}
