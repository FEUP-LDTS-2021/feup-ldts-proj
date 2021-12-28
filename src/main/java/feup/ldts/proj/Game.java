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


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Game {
    List<List<Character>> roomLayout;

    public Game() {
        roomLayout = new ArrayList<>();
        //TerminalSize terminalSize = new TerminalSize(20, 20);
        //DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
    }

    public void loadRoom(int roomNum, int depth) {
        File file = new File("depth" + depth, "room" + roomNum);
    }

    public List<List<Character>> getRoomLayout() {
        return roomLayout;
    }


}
