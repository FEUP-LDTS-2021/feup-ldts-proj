package feup.ldts.proj.model.room;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Room {
    private final int depth;
    private final String BG_COLOR = Game.Colors.get("Dirt");

    Player player;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Bullet> bullets;
    Position gate;

    //constructors

    public Room(int depth) {
        this.depth = depth;
    }

    //getters

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Position getGate() { return gate; }

    public Player getPlayer() {
        return player;
    }

    public List<Bullet> getBullets() { return bullets; }

    //setters

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    public void setBullets(List<Bullet> bullets) { this.bullets = bullets; }

    public void setGate(Position gate) { this.gate = gate; }

    public void setPlayer(Player player) { this.player = player; }

    //collision detection methods

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return true;
        return false;
    }

    public boolean isMonster(Position position) {
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position)) return true;
        return false;
    }

    public boolean isPlayer(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean isGate(Position position) {
        return gate.equals(position);
    }

    /*
    public boolean isItem() ;D (coming soon)
     */
}
