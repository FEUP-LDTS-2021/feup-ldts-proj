package feup.ldts.proj.model.game.room;

import feup.ldts.proj.controller.game.elements.observers.BulletObserver;
import feup.ldts.proj.controller.game.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.game.elements.observers.PlayerObserver;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Passage;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.Wall;
import feup.ldts.proj.model.game.elements.bullets.Bullet;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.game.elements.monsters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int depth;

    Player player;
    Passage passage;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<MonsterBullet> monsterBullets;
    private List<PlayerBullet> playerBullets;

    //--------------------------------------constructor--------------------------------------

    public Room(int depth) {
        this.depth = depth;
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        monsterBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
    }

    //----------------------------------------getters-----------------------------------------

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

    //----------------------------------------setters-----------------------------------------

    public void setWalls(List<Wall> walls) { this.walls = walls; }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
        setObservers();
    }

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
                    if (monster.getPosition().equals(player.getPosition()))  //checks if a monster walked on top of a player
                        monster.bite(player);

                    for(int i = 0; i < playerBullets.size(); i++) { //checks if a monster walked on top of a PlayerBullet
                        PlayerBullet bullet = playerBullets.get(i);
                        if (monster.getPosition().equals(bullet.getPosition())) {
                            monster.decreaseHP(bullet.getDamage());
                            bullet.alertObserversDecayed();
                            i--;
                        }
                    }
                }
            });
        }

        player.addPlayerObserver(new PlayerObserver() {
            @Override
            public void positionChanged(Player player) {
                for (Monster monster : monsters) {             //checks if a player has walked on top of a monster
                    if (monster.getPosition().equals(player.getPosition()))
                        monster.bite(player);
                }

                for(int i = 0; i < monsterBullets.size(); i++) { //checks if the player walked on top of a MonsterBullet
                    MonsterBullet bullet = monsterBullets.get(i);
                    if (player.getPosition().equals(bullet.getPosition())) {
                        player.decreaseHP(bullet.getDamage());
                        bullet.alertObserversDecayed();
                        i--;
                    }
                }

                // checks if the player walked on top of a potion

                // checks if the player walked on top of a weapon

            }
        });
    }

    //-------------------------------------other functions-------------------------------------

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

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return true;
        return false;
    }

    public boolean isMonster(Position position) {
        for (Monster monster : monsters)
            if (monster.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isPlayer(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean isPassage() {
        return player.getPosition().equals(passage.getPosition());
    }
}
