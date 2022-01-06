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

    /*public void draw(TextGraphics graphics) throws IOException {
        graphics.setBackgroundColor(TextColor.Factory.fromString(BG_COLOR));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(20, 20), ' ');
        if (monsters.size() > 0) graphics.setForegroundColor(TextColor.Factory.fromString(BG_COLOR));
        else graphics.setForegroundColor(TextColor.Factory.fromString(Game.Colors.get("Black")));
        graphics.putString(new TerminalPosition(gate.getX(), gate.getY()), "O");

        for (Wall wall : walls) wall.draw(graphics);
        for (Monster monster : monsters) monster.draw(graphics);
        for (Bullet bullet : bullets) bullet.draw(graphics);
        player.draw(graphics);
    }*/

    public void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowLeft) {
            player.setFacingDirection(Element.Direction.LEFT);
            movePlayer(player.getPosition().getLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            player.setFacingDirection(Element.Direction.RIGHT);
            movePlayer(player.getPosition().getRight());

        } else if (key.getKeyType() == KeyType.ArrowDown) {
            player.setFacingDirection(Element.Direction.DOWN);
            movePlayer(player.getPosition().getDown());
        } else if (key.getKeyType() == KeyType.ArrowUp) {
            player.setFacingDirection(Element.Direction.UP);
            movePlayer(player.getPosition().getUp());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'x') {
            shoot();
        }
    }

    private void movePlayer(Position position) {
        if (canExecuteMovement(position))
            player.setPosition(position);
        else {
            for (Monster monster : monsters)
                if (position.equals(monster.getPosition()))
                    player.decreaseHP(monster.getDamage());
        }
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
            for (int i = 0; i < bullets.size(); i++) {
                if (monster.getPosition().equals(bullets.get(i).getPosition())) {
                    monster.decreaseHP(bullets.get(i).getDamage());
                    bullets.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getHP() == 0) {
                monsters.remove(i);
                i--;
            }
        }

    }

    public void moveBullets() { //to be checked later

        BULLET_LOOP:
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isAtLimit()) {
                bullets.remove(i);
                continue;
            }
            Position bulletPos = bullets.get(i).moveBullet();
            if (checkWallCollision(bulletPos)) {
                bullets.remove(i);
                i--;
                continue;
            }
            for (int j = 0; j < monsters.size(); j++) {
                if (monsters.get(j).getPosition().equals(bulletPos)) {
                    monsters.get(j).decreaseHP(bullets.get(i).getDamage());
                    if (monsters.get(j).getHP() == 0) {
                        monsters.remove(j);
                        j--;
                    }
                    bullets.remove(i);
                    i--;
                    //goes on to the next bullet
                    continue BULLET_LOOP;
                }
            }
            bullets.get(i).setPosition(bulletPos);
            bullets.get(i).incrementDistanceTravelled();
        }
    }

    //-----------

    private void shoot() {
        if (bullets.size() >= player.getWeapon().getCapacity()) return; //player can't shoot yet
        for (Bullet bullet : bullets) {
            if (bullet.getPosition().equals(player.getPosition()))
                return; //player is trying to shoot inside another bullet
        }
        bullets.add(createBullet());
    }

    private Bullet createBullet() {
        return new Bullet(player.getPosition().getX(), player.getPosition().getY(), player.getWeapon().getRange(), player.getWeapon().getDamage(), player.getFacingDirection());
    }

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


    public boolean canExecuteMovement(Position position) {
        return (!(checkWallCollision(position) || checkMonsterCollision(position) || checkPlayerCollision(position)));
    }

    public boolean gateCollision() {
        return player.getPosition().equals(gate);
    }

}
