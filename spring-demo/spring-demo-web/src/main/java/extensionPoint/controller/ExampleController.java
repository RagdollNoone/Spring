package extensionPoint.controller;

import extensionPoint.api.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example/")
public class ExampleController {
    @Autowired
    private Car myCar;

    @RequestMapping("test")
    public void test() {
        System.out.println("[ExampleController][test]start");
        System.out.println(myCar);
    }
}
