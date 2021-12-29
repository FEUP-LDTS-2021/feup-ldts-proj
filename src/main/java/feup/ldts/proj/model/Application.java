package feup.ldts.proj.model;

import feup.ldts.proj.Game;

import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Game game = new Game();
        game.run();
    }
}
