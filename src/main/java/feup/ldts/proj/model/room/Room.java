package feup.ldts.proj.model.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.*;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int depth;
    private final String BG_COLOR = Game.Colors.get("Dirt");

    Player player;
    Passage passage;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Bullet> bullets;

    //constructors

    public Room(int depth) {
        this.depth = depth;
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    //getters

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Passage getPassage() { return passage; }

    public Player getPlayer() {
        return player;
    }

    public List<Bullet> getBullets() { return bullets; }

    public int getDepth() {
        return depth;
    }

    //setters

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }

    public void setBullets(List<Bullet> bullets) { this.bullets = bullets; }

    public void setPassage(Passage passage) { this.passage = passage; }

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

    public boolean isPassage(Position position) {
        return passage.getPosition().equals(position);
    }

    /*
    public boolean isItem() ;D (coming soon)
     */

    //------------------------------------------------------------------------------------------------

    //temporary methods to move the game entities


    //----------- movement and collision down below

    private boolean checkWallCollision(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    private boolean checkMonsterCollision(Position position) {
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position))
                return true;
        return false;
    }

    private boolean checkPlayerCollision(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean passageCollision() {
        return player.getPosition().equals(passage.getPosition());
    }

    public boolean canExecuteMovement(Position position) {
        return (!(checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position)));
    }
}
