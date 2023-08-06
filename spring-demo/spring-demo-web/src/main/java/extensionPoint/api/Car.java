package extensionPoint.api;

import extensionPoint.component.Tyre;

public class Car implements Engine {
    private String name;
    private Tyre tyre;

    @Override
    public void start() {
        System.out.println("car start");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }
}
