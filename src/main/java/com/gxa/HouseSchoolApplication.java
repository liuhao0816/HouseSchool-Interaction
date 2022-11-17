package com.gxa;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement//开启事务管理
@SpringBootApplication
@MapperScan(basePackages = {"com.gxa.modules.sys.mapper"})
public class HouseSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(HouseSchoolApplication.class, args);
    }

}
