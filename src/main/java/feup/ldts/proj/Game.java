package feup.ldts.proj;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.gui.LanternaGUI;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import feup.ldts.proj.viewer.room.RoomViewer;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.BreakIterator;
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
    RoomViewer viewer;

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
        this.room = new RoomBuilder(0, 1).createRoom(new Player(10, 10));
        this.viewer = new RoomViewer(gui);
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

    public void run() throws IOException, URISyntaxException {
        int FPS = 60;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0, lastBulletMovement = 0;

        while (true) {

            long startTime = System.currentTimeMillis();

            if (room.getPlayer().getHP() == 0) {
                depth = 0;
                updateRoom(depth, 1);
            }

            viewer.draw(room);

            System.out.println(room.getMonsters().isEmpty());
            System.out.println(room.getPassage().getPosition());
            if (room.getMonsters().isEmpty())
                viewer.drawPassage(room);


            GUI.ACTION nextAction = gui.getAction();
            if (nextAction == GUI.ACTION.EXIT) {
                gui.close();
                break;
            }
            room.processKey(nextAction);

            if (room.passageCollision() && room.getMonsters().isEmpty()) {
                depth++;

                if (depth == 3) {
                    depth = 0;
                    updateRoom(depth, 1);
                }
                int newRoom = new Random().nextInt(3) + 1;
                updateRoom(depth, newRoom);
            }

            if (startTime - lastMonsterMovement > 500) {
                room.moveMonsters();
                lastMonsterMovement = startTime;
            }

            if (startTime - lastBulletMovement > 250) {
                room.moveBullets();
                lastBulletMovement = startTime;
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            if (sleepTime > 0) try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateRoom(int depth, int roomNum) throws FileNotFoundException, URISyntaxException {
        this.room = new RoomBuilder(depth, roomNum).createRoom(new Player(10, 10));
    }
}
