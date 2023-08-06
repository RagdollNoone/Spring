package extensionPoint;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"extensionPoint"}, exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
public class ExtensionPointApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExtensionPointApplication.class);
    }
}
