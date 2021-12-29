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
import feup.ldts.proj.model.elements.Player;
import org.w3c.dom.Text;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Game {
    List<String> roomLayout;
    Screen screen;
    Player player;
    private final int NUM_ROWS = 20;
    private final int NUM_COLS = 20;

    public Game() {
        roomLayout = new ArrayList<String>();
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void loadRoom(int roomNum, int depthNum) throws URISyntaxException, IOException {
        String roomPath = "rooms/depth" + depthNum + '/' + "room" + roomNum + ".txt";
        URL resource = getClass().getClassLoader().getResource(roomPath);
        File roomLayoutFile = new File(resource.toURI());

        try {
            Scanner fileReader = new Scanner(roomLayoutFile);
            while (fileReader.hasNextLine()) {
                String roomRow = fileReader.nextLine();
                System.out.println(roomRow); //for debugging purposes
                roomLayout.add(roomRow);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }

        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#003366"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(NUM_COLS, NUM_ROWS), ' ');
        screen.refresh();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (roomLayout.get(i).charAt(j) != ' ') {
                    screen.setCharacter(j, i, TextCharacter.fromCharacter(roomLayout.get(i).charAt(j))[0]);
                }
            }
        }
    }

    public List<String> getRoomLayout() {
        return roomLayout;
    }

    public void run() throws IOException, URISyntaxException {
        loadRoom(1, 0);
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
        }
    }

    private void draw() throws IOException {
        player.draw(screen);
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowLeft) player.movePlayerLeft();
        else if (key.getKeyType() == KeyType.ArrowRight) player.movePlayerRight();
        else if (key.getKeyType() == KeyType.ArrowDown) player.movePlayerDown();
        else if (key.getKeyType() == KeyType.ArrowUp) player.movePlayerUp();
    }

}
