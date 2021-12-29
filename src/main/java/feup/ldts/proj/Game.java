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
import feup.ldts.proj.model.elements.Player;
import org.w3c.dom.Text;


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

    public Game() {
        roomLayout = new ArrayList<String>();
        try {
            Terminal terminal = new DefaultTerminalFactory().setInitialTerminalSize(new TerminalSize(20, 20)).createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            player = new Player(10, 10);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRoom(int roomNum, int depthNum) throws URISyntaxException {
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
    }

    public List<String> getRoomLayout() {
        return roomLayout;
    }

    public void run() throws IOException {
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
            if (key.getKeyType() == KeyType.EOF) break;
            processKey(key);
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.newTextGraphics().setBackgroundColor(TextColor.Factory.fromString("#C9F4DA"));
        screen.newTextGraphics().fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 20), ' ');
        screen.refresh();
        player.draw(screen);
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowLeft) player.movePlayerLeft();
        else if (key.getKeyType() == KeyType.ArrowRight) player.movePlayerRight();
        else if (key.getKeyType() == KeyType.ArrowDown) player.movePlayerDown();
        else if (key.getKeyType() == KeyType.ArrowUp) player.movePlayerUp();
    }

}
