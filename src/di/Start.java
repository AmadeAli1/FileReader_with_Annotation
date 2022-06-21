package di;

public class Start {

    public static void main(String[] args) {
        var context = new Application();

        var car = context.getInstance(Car.class);
        var cars = context.getInstance(Car.class);

        cars.print();
        car.print();

    }

}
