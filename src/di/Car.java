package di;

@Provide
public class Car {
    @Inject
    private Engine engine;

    public Car() {
    }

    public void print() {
        System.out.println(engine.toString());
    }

}
