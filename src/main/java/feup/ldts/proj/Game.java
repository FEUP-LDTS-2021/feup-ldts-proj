package feup.ldts.proj;

import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.room.RoomController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.gui.LanternaGUI;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import feup.ldts.proj.states.GameState;
import feup.ldts.proj.states.State;
import feup.ldts.proj.viewer.room.RoomViewer;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Game {
    private final int NUM_ROWS = 20;
    private final int NUM_COLS = 20;
    int depth;
    GUI gui;
    Room room;

    private State state;
    private Player player;

    public static final HashMap<String, String> Colors = new HashMap<String, String>() {{ //should probably be somewhere else?
        //others or not used
        put("LightGreen", "#C9F4DA");
        put("Blurple", "#5D5CAF"); //walls
        put("Dirt", "#634220"); //floor
        put("Black", "#000000"); //passage

        //bullet colors
        put("Golden", "#FFD966");
        put("SlightRust", "#94751B");
        put("Rust", "#291e00");

        //monster colors
        put("Red", "#D20F23");
        put("Pink", "#B01549");
        put("Purple", "#691b51");

        //player colors
        put("HealthyGreen", "#02f751");
        put("Green", "#19b33a");
        put("DarkGreen", "#1d871a");
        put("WoundedGreen", "#1a610e");
        put("DyingGreen", "#103d02");
    }};

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI(NUM_COLS, NUM_ROWS);
        this.player = new Player(-1, -1);
        this.state = new GameState(new RoomBuilder(0, 1).createRoom(player));
    }


    public static void main(String[] args) throws URISyntaxException, FontFormatException {
        try {
            Game game = new Game();
            game.run();
            if (game.gui.getAction() == GUI.ACTION.EXIT) game.gui.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException, URISyntaxException {
        int FPS = 60;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        gui.close();
    }

    public void setState(State state) {
        this.state = state;
    }
}
