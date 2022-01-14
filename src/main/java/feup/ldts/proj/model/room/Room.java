package feup.ldts.proj.model.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.observers.BulletObserver;
import feup.ldts.proj.controller.elements.observers.MonsterObserver;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.*;
import feup.ldts.proj.model.elements.bullets.Bullet;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.elements.monsters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int depth;
    private final String BG_COLOR = Game.Colors.get("Dirt");

    Player player;
    Passage passage;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<MonsterBullet> monsterBullets;
    private List<PlayerBullet> playerBullets;

    //constructors

    public Room(int depth) {
        this.depth = depth;
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        monsterBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
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

    public List<MonsterBullet> getMonsterBullets() { return monsterBullets; }

    public List<PlayerBullet> getPlayerBullets() { return playerBullets; }

    public int getDepth() {
        return depth;
    }

    //setters

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
        setObservers();
    }

    public void setPlayerBullets(List<PlayerBullet> playerBullets) { this.playerBullets = playerBullets; }

    public void setMonsterBullets(List<MonsterBullet> monsterBullets) { this.monsterBullets = monsterBullets; };

    public void setPassage(Passage passage) { this.passage = passage; }

    public void setPlayer(Player player) { this.player = player; }

    private void setObservers() {
        for (Monster monster : monsters) {
            monster.addMonsterObserver(new MonsterObserver() {
                @Override
                public void hpChanged(Monster monster) {
                    if (monster.getHP() <= 0)
                        monsters.remove(monster);
                }

                @Override
                public void positionChanged(Monster monster) {
                    //do nothing
                }
            });
        }
    }

    //other functions

    public void addMonsterBullet (MonsterBullet bullet) {
        monsterBullets.add(bullet);
        bullet.addBulletObserver(new BulletObserver() {
            @Override
            public void decayed(Bullet bullet) {
                monsterBullets.remove(bullet);
            }
        });
    }

    public void addPlayerBullet (PlayerBullet bullet) {
        playerBullets.add(bullet);
        bullet.addBulletObserver(new BulletObserver() {
            @Override
            public void decayed(Bullet bullet) {
                playerBullets.remove(bullet);
            }
        });
    }


    //collision detection methods

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return true;
        return false;
    }

    public boolean isMonster(Position position) {
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position)) {

                return true;
            }
        return false;
    }

    public boolean isPlayer(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean isPassage(Position position) {
        return passage.getPosition().equals(position);
    }



    //----------- movement and collision down below

    private boolean checkPlayerCollision(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean passageCollision() {
        return player.getPosition().equals(passage.getPosition());
    }

    public boolean canExecuteMovement(Position position) {
        return (!(isWall(position) || isMonster(position) || checkPlayerCollision(position)));
    }
}
