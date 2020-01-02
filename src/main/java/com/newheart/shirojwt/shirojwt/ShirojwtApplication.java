package com.newheart.shirojwt.shirojwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages ={"com.newheart.shirojwt.shirojwt.mapper"} )
public class ShirojwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirojwtApplication.class, args);
    }

}
