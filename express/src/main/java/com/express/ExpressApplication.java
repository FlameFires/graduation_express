package com.express;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.express.mapper")
public class ExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressApplication.class, args);
    }

}
