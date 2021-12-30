package feup.ldts.proj.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.Wall;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
    private final int depthNum;
    private final String BG_COLOR = Game.Colors.get("LightGreen");

    Player player;
    private List<Wall> walls;
    private List<Monster> monsters;
    Position gate;

    public Room(URI roomURI, int depthNum) throws URISyntaxException, FileNotFoundException {
        this.depthNum = depthNum;
        File roomLayoutFile = new File(roomURI);

        walls = new ArrayList<Wall>();
        monsters = new ArrayList<Monster>();


        List<String> roomLayout = readRoomFile(roomLayoutFile);
        loadRoom(depthNum, roomLayout);
    }

    private List<String> readRoomFile(File file) throws FileNotFoundException {
        List<String> fileContent = new ArrayList<>();
        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine())
            fileContent.add(fileReader.nextLine());

        return fileContent;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Position getGate() {
        return gate;
    }

    public Player getPlayer() {
        return player;
    }

    private void loadRoom(int depthNum, List<String> roomLayout) {
        for (int row = 0; row < roomLayout.size(); row++) {
            String currentRow = roomLayout.get(row);
            for (int col = 0; col < currentRow.length(); col++)
                switch (currentRow.charAt(col)) {
                    case '#':
                        walls.add(new Wall(col, row));
                        break;
                    case 'M':
                        monsters.add(new Monster(col, row, depthNum));
                        break;
                    case 'O':
                        gate = new Position(col, row);
                        break;
                    case 'X':
                        player = new Player(col, row);
                        break;
                }
        }
    }

    public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(BG_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 20), ' ');
        graphics.putString(new TerminalPosition(gate.getY(), gate.getX()), "O");

        for (Wall wall : walls) wall.draw(graphics);
        for (Monster monster : monsters) monster.draw(graphics);
        player.draw(graphics);
    }

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowLeft) movePlayer(player.getPosition().getLeft());
        else if (key.getKeyType() == KeyType.ArrowRight) movePlayer(player.getPosition().getRight());
        else if (key.getKeyType() == KeyType.ArrowDown) movePlayer(player.getPosition().getDown());
        else if (key.getKeyType() == KeyType.ArrowUp) movePlayer(player.getPosition().getUp());
    }

    private void movePlayer(Position position) {
        if (canExecuteMovement(position))
            player.setPosition(position);
    }

    public void moveMonsters() {
        for (Monster monster : monsters) {
            Position monsterPos = monster.moveMonster();
            if (canExecuteMovement(monsterPos))
                monster.setPosition(monsterPos);
            else {
                if (player.getPosition().equals(monsterPos))
                    player.decreaseHP(monster.getDamage());
            }
        }
    }

    //----------- movement and collision down below

    public boolean canExecuteMovement(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;

        for (Monster monster : monsters)
            if (monster.getPosition().equals(position)) {
                if (player.getPosition().equals(position))
                    player.decreaseHP(monster.getDamage());
                return false;
            }

        if (player.getPosition().equals(position))
            return false;

        return true;
    }
}
