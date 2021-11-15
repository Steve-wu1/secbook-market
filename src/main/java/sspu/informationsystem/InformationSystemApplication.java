package sspu.informationsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages = {"sspu.informationsystem.mapper"})
public class InformationSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(InformationSystemApplication.class, args);
    }

}
