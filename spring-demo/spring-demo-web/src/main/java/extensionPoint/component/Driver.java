package extensionPoint.component;

import org.springframework.stereotype.Component;

@Component
public class Driver {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
