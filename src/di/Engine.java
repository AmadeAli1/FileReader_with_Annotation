package di;

public class Engine {
    private String version;

    public Engine(String version) {
        this.version = version;
    }

    public Engine() {
        version="8";
        System.out.println("Call");
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "version='" + version + '\'' +
                '}';
    }

}
