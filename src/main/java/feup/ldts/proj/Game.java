package feup.ldts.proj;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.TerminalFactory;


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

    public Game() {
        roomLayout = new ArrayList<String>();
        //TerminalSize terminalSize = new TerminalSize(20, 20);
        //DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
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

}
