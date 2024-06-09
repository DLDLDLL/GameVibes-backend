package com.example.gamevibe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gamevibe.mapper")
public class GamevibeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamevibeApplication.class, args);
    }

}
