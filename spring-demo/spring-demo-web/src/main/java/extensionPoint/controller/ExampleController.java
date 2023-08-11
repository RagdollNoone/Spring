package extensionPoint.controller;

import extensionPoint.api.Car;
import extensionPoint.component.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("example/")
public class ExampleController {
    @Autowired
    private Car myCar;

    @Autowired
    private Driver driver;

    private ApplicationContext applicationContext;

    @RequestMapping("test")
    public void test() {
        System.out.println("[ExampleController][test]start");
        System.out.println(myCar);

        System.out.println("just for break");
    }


}
