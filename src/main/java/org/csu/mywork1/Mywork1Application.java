package org.csu.mywork1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.mywork1.persistence")
public class Mywork1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mywork1Application.class, args);
    }

}
