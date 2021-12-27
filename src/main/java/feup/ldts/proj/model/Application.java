package feup.ldts.proj.model;

import feup.ldts.proj.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
