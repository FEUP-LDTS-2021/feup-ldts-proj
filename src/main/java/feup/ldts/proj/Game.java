package feup.ldts.proj;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import feup.ldts.proj.model.room.Room;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.io.File;

public class Game {
    Room currentRoom;
    Screen screen;
    private final int NUM_ROWS = 20;
    private final int NUM_COLS = 20;
    public static final HashMap<String, String> Colors = new HashMap<String, String>() {{
        //others or non used
        put("LightGreen", "#C9F4DA");
        put("Blurple", "#5D5CAF");
        put("Dirt", "#634220");
        put("Black", "#000000");
        put("White", "#FFFFFF");
        put("Gray", "#4D5D53");
        put("DarkGray", "#3B463F");

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

    int depth;

    public Game() {
        try {
            URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
            File fontFile = new File(resource.toURI());
            Font font =  Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);

            DefaultTerminalFactory factory = new DefaultTerminalFactory();

            Font loadedFont = font.deriveFont(Font.PLAIN, 25);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(NUM_COLS, NUM_ROWS));

            //factory.setMouseCaptureMode(MouseCaptureMode.CLICK);
            Terminal terminal = factory.createTerminal();

            /*((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });*/

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            depth = 0;
            currentRoom = new Room(constructRoomFileURI(depth, 1), depth);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException, URISyntaxException {
        //code extracted from Professor Andre Restivo's hero-solid repository, might be changed later if needed
        int FPS = 60;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0, lastBulletMovement = 0;

        while (true) {

            long startTime = System.currentTimeMillis();

            if (currentRoom.getPlayer().getHP() == 0) {
                depth = 0;
                updateRoom(depth, 1); //depth 0 only has 1 room;
            }

            draw();

            KeyStroke key = screen.pollInput();
            if (key != null) {

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                if (key.getKeyType() == KeyType.EOF) break;
                currentRoom.processKey(key);

            }

            //notice that if the file name is roomX then the map has X monsters
            if (currentRoom.gateCollision() && currentRoom.getMonsters().isEmpty()) {
                depth++;

                //temporary, we must load the Boss room (to be designed later)
                if (depth == 3) {
                    depth = 0;
                    updateRoom(depth, 1);
                } else {
                    int newRoomNum = new Random().nextInt(3) + 1; //random number between 1 and 3, indicating the room number
                    updateRoom(depth, newRoomNum);
                }
            }

            if (startTime - lastMonsterMovement > 500) {
                currentRoom.moveMonsters();
                lastMonsterMovement = startTime;
            }

            if (startTime - lastBulletMovement > 250) {
                currentRoom.moveBullets();
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

    public static void main(String[] args) throws IOException, URISyntaxException {
        Game game = new Game();
        game.run();
    }

    private void draw() throws IOException {
        screen.doResizeIfNecessary();
        screen.clear();
        currentRoom.draw(screen.newTextGraphics());
        screen.refresh();
    }

    
    private void updateRoom(int newDepth, int newRoomNum) throws FileNotFoundException, URISyntaxException {
        this.currentRoom = new Room(constructRoomFileURI(newDepth, newRoomNum), newDepth);
    }

}
