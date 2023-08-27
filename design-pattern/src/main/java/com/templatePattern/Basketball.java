package com.templatePattern;

public class Basketball extends Game {
    @Override
    void initialize() {
        System.out.println("[Basketball][initialize]");
    }

    @Override
    void startPlay() {
        System.out.println("[Basketball][startPlay]");
    }

    @Override
    void endPlay() {
        System.out.println("[Basketball][endPlay]");
    }
}
