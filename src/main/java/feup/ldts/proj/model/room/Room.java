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

    public void processKey(GUI.ACTION action) {
        if (action == GUI.ACTION.LEFT) {
            movePlayer(player.getPosition().getLeft());
            player.setFacingDirection(Element.Direction.LEFT);
        }
        else if (action == GUI.ACTION.RIGHT) {
            movePlayer(player.getPosition().getRight());
            player.setFacingDirection(Element.Direction.RIGHT);
        }
        else if (action == GUI.ACTION.DOWN) {
            movePlayer(player.getPosition().getDown());
            player.setFacingDirection(Element.Direction.DOWN);
        }
        else if (action == GUI.ACTION.UP) {
            movePlayer(player.getPosition().getUp());
            player.setFacingDirection(Element.Direction.UP);
        }
        else if (action == GUI.ACTION.SHOOT) {
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

    private void shoot() {
        if (bullets.size() >= player.getWeapon().getCapacity()) return; //player can't shoot yet
        for (Bullet bullet : bullets) {
            if (bullet.getPosition().equals(player.getPosition())) return; //player is trying to shoot inside another bullet
        }
        bullets.add(player.createBullet());
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

    public boolean passageCollision() {
        return player.getPosition().equals(passage.getPosition());
    }
}
