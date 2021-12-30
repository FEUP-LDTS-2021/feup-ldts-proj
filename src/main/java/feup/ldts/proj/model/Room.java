package feup.ldts.proj.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.Wall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
    private final int depthNum;
    private final int roomNum;

    Player player;
    private List<Wall> walls;
    private List<Monster> monsters;
    Position gate;

    public Room(int depthNum, int roomNum, Player player) throws URISyntaxException, FileNotFoundException {
        this.depthNum = depthNum;
        this.roomNum = roomNum;

        String roomPath = "rooms/depth" + depthNum + '/' + "room" + roomNum + ".txt";
        URL resource = getClass().getClassLoader().getResource(roomPath);
        File roomLayoutFile = new File(resource.toURI());

        walls = new ArrayList<Wall>();
        monsters = new ArrayList<Monster>();

        List<String> roomLayout = readRoomFile(roomLayoutFile);
        loadRoom(depthNum, roomLayout, player);
        this.player = player;
    }

    private List<String> readRoomFile(File filePath) throws FileNotFoundException {

        return null;
    }

    public List<Wall> getWalls() {return walls;}

    public List<Monster> getMonsters() {return monsters;}

    public Position getGate() {return gate;}

    public Player getPlayer() {return player;}

    private void loadRoom(int depthNum, List<String> roomLayout, Player player) {

    }

    public void draw(TextGraphics graphics) throws IOException {

    }
}
