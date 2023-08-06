package extensionPoint.component;

public class Tyre {
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "color='" + color + '\'' +
                '}';
    }
}
