package com.templatePattern;

public class TemplatePatternTest {
    public static void main(String[] args) {
        Game game = new Basketball();
        game.play();

        game = new Football();
        game.play();
    }
}
