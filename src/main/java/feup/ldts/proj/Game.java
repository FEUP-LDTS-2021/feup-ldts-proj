package feup.ldts.proj;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.gui.LanternaGUI;
import feup.ldts.proj.model.menu.MainMenu;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.menu.WeaponMenu;
import feup.ldts.proj.states.MainMenuState;
import feup.ldts.proj.states.State;
import feup.ldts.proj.states.WeaponMenuState;

import java.awt.*;
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

    public static final HashMap<String, String> Colors = new HashMap<String, String>()
    {{
        put("LightGreen", "#C9F4DA");
        put("Blurple", "#5D5CAF"); //walls
        put("Dirt", "#634220"); //floor
        put("Black", "#000000"); //passage
        put("White", "#ffffff");

        //bullet colors
        put("Golden", "#FFD966");
        put("SlightRust", "#94751B");
        put("Rust", "#291e00");
        put("EvilPurple", "#9b00e3");
        put("VanishingPurple", "#700cad");
        put("GonePurple", "#4a107d");

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
        this.state = new WeaponMenuState(new WeaponMenu());
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
