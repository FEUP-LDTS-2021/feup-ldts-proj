package feup.ldts.proj;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.gui.LanternaGUI;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import feup.ldts.proj.viewer.room.RoomViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

//-----------------------------------------------------------------------------------------------------------//
//                                                                                                           //
// This first portion of the game was heavily inspired by the Hero project developed in the Labs.            //
// We were also guided by Professor Andre Restivo, following a lot of his principles and work at hero-solid. //
// Check the code report for more details.                                                                   //
// Features to implement in the future:                                                                      //
//  - Add a controller for each entity that needs one                                                        //
//  - Add different types of Weapons for our Player                                                          //
//  - Add various items which will boost the Player in different ways                                        //
//  - Modify the coloring scheme and also edit the current font so it looks more RPG                         //
//  - Implement the Boss Fight                                                                               //
//                                                                                                           //
//-----------------------------------------------------------------------------------------------------------//

public class Game {
    private final int NUM_ROWS = 20;
    private final int NUM_COLS = 20;
    int depth;
    GUI gui;
    Room room;

    public static final HashMap<String, String> Colors = new HashMap<String, String>() {{ //should probably be somewhere else?
        //others or not used
        put("LightGreen", "#C9F4DA");
        put("Blurple", "#5D5CAF"); //walls
        put("Dirt", "#634220"); //floor
        put("Black", "#000000"); //gate

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
        this.room = new RoomBuilder(0, 1).createRoom(new Player(10, 10));
    }

    public void displayGame() throws IOException {
        RoomViewer viewer = new RoomViewer(gui);
        viewer.draw(room);
    }

    public static void main(String[] args) throws URISyntaxException, FontFormatException {
        try {
            Game game = new Game();
            game.displayGame();
            if (game.gui.getAction() == GUI.ACTION.EXIT) game.gui.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
