package com.example.gamevibe.strategy;

import org.springframework.stereotype.Component;

@Component
public interface MessageStrategy {

    boolean check(String arg);

    void send(Object data);

    void accept(Object data);

}
