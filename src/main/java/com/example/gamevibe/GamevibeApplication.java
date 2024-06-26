package com.example.gamevibe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.gamevibe.mapper")
@EnableScheduling
public class GamevibeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamevibeApplication.class, args);
    }

}
