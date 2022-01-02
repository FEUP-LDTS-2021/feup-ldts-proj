package feup.ldts.proj;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.TerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import feup.ldts.proj.model.Room;
import feup.ldts.proj.model.elements.Player;
import org.w3c.dom.Text;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.io.File;
import java.util.List;

public class Game {
    Room currentRoom;
    Screen screen;
    Player player;
    private final int NUM_ROWS = 20;
    private final int NUM_COLS = 20;
    public static final HashMap<String, String> Colors = new HashMap<String, String>() {{
        put("LightGreen", "#C9F4DA");
        put("Red", "#D20F23");
        put("DarkGreen","#017727");
        put("Blurple", "#5D5CAF");
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

            Terminal terminal = factory.createTerminal();
            ((AWTTerminalFrame)terminal).addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                }
            });

            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();

            player = new Player(10, 10);
            depth = 0;
            currentRoom = new Room(constructRoomFileURI(depth, 1), 1); //COMEÇAR NO 0 !! MUDAR O depthNum para ser depth em vez de uma constantef
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
        int FPS = 20;
        int frameTime = 1000 / FPS;
        long lastMonsterMovement = 0;

        while (true) {
            long startTime = System.currentTimeMillis();

            draw();

            KeyStroke key = screen.pollInput();
            if (key != null) {
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                if (key.getKeyType() == KeyType.EOF) break;
                currentRoom.processKey(key);
            }

            int newRoomNum = new Random().nextInt(3) + 1; //random number between 1 and 3, indicating the room number
            //notice that if the file name is roomX then the map has X monsters
            if (currentRoom.gateCollision()) {
                depth++;
                updateRoom(depth, newRoomNum);
                /*
                    if (depth == 3) {
                        ...
                        depth 3 is the final depth so we must load the bossroom - to be designed later
                    }
                 */
            }

            if (startTime - lastMonsterMovement > 500) {
                currentRoom.moveMonsters();
                lastMonsterMovement = startTime;
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

    private void draw() throws IOException {
        screen.clear();
        currentRoom.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public static URI constructRoomFileURI() {
        try {
            URL resource = Game.class.getClassLoader().getResource("rooms/test/testRoom.txt");
            return resource.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URI constructRoomFileURI(int depthNum, int roomNum) {
        try {
        String roomPath = "rooms/depth" + depthNum + '/' + "room" + roomNum + ".txt";
        URL resource = getClass().getClassLoader().getResource(roomPath);
        return resource.toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void updateRoom(int newDepth, int newRoomNum) throws FileNotFoundException, URISyntaxException {
        this.currentRoom = new Room(constructRoomFileURI(newDepth, newRoomNum), 1);
    }

}
