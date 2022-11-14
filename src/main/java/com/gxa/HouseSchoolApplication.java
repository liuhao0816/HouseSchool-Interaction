package com.gxa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.gxa.modules.sys.mapper"})
//@EnableScheduling
public class HouseSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseSchoolApplication.class, args);
    }

}
