package feup.ldts.proj.model.game.room;

import feup.ldts.proj.controller.game.elements.observers.BulletObserver;
import feup.ldts.proj.controller.game.elements.observers.MonsterObserver;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Passage;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.Wall;
import feup.ldts.proj.model.game.elements.bullets.Bullet;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.game.elements.items.Item;
import feup.ldts.proj.model.game.elements.items.WeaponItem;
import feup.ldts.proj.model.game.elements.items.potions.HealingPotion;
import feup.ldts.proj.model.game.elements.items.potions.MaxHealthPotion;
import feup.ldts.proj.model.game.elements.items.potions.TimePotion;
import feup.ldts.proj.model.game.elements.monsters.Boss;
import feup.ldts.proj.model.game.elements.monsters.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private final int depth;

    Player player;
    Passage passage;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<MonsterBullet> monsterBullets;
    private List<PlayerBullet> playerBullets;
    private List<Item> items;

    //--------------------------------------constructor--------------------------------------

    public Room(int depth) {
        this.depth = depth;
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        monsterBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        items = new ArrayList<>();
    }

    //----------------------------------------getters-----------------------------------------

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Passage getPassage() {
        return passage;
    }

    public Player getPlayer() {
        return player;
    }

    public List<MonsterBullet> getMonsterBullets() {
        return monsterBullets;
    }

    public List<PlayerBullet> getPlayerBullets() {
        return playerBullets;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getDepth() {
        return depth;
    }

    public Boss getBoss() {
        for (Monster monster : monsters)
            if (monster instanceof Boss)
                return (Boss) monster;
        return null;
    }

    //----------------------------------------setters-----------------------------------------

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setPassage(Passage passage) {
        this.passage = passage;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMonsterObservers() {
        for (Monster monster : monsters)
            monster.addMonsterObserver(new MonsterObserver() {
                @Override
                public void hpChanged(Monster monster) {
                    if (monster.getHP() <= 0) {
                        createItem(monster.getPosition());
                        monsters.remove(monster);
                    }
                }

                @Override
                public void positionChanged(Monster monster) {
                    if (monster.getPosition().equals(player.getPosition()))  //checks if a monster walked on top of a player
                        monster.bite(player);

                    for (int i = 0; i < playerBullets.size(); i++) { //checks if a monster walked on top of a PlayerBullet
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

    public void setPlayerObserver() {
        player.addPlayerObserver(player -> { //player has moved
                    for (Monster monster : monsters)             //checks if a player has walked on top of a monster
                        if (monster.getPosition().equals(player.getPosition()))
                            monster.bite(player);

                    for (int i = 0; i < monsterBullets.size(); i++) { //checks if the player walked on top of a MonsterBullet
                        MonsterBullet bullet = monsterBullets.get(i);
                        if (player.getPosition().equals(bullet.getPosition())) {
                            player.decreaseHP(bullet.getDamage());
                            bullet.alertObserversDecayed();
                            i--;
                        }
                    }

                    for (int i = 0; i < items.size(); i++) {// checks if the player walked on top of an item
                        Item item = items.get(i);
                        if (player.getPosition().equals(item.getPosition())) {
                            item.onPickup(player);
                            items.remove(i);
                            i--;
                        }
                    }
                }
        );
    }

    public void setObservers() {
        setMonsterObservers();
        setPlayerObserver();
    }

    //-------------------------------------other functions-------------------------------------

    public void addMonsterBullet(MonsterBullet bullet) {
        monsterBullets.add(bullet);
        bullet.addBulletObserver(new BulletObserver() {
            @Override
            public void decayed(Bullet bullet) {
                monsterBullets.remove(bullet);
            }
        });
    }

    public void addPlayerBullet(PlayerBullet bullet) {
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

    public boolean isItem(Position position) {
        for (Item item : items)
            if (item.getPosition().equals(position))
                return true;
        return false;
    }

    public boolean isPlayer(Position position) {
        return player.getPosition().equals(position);
    }

    public boolean isBullet(Position position) {
        for (PlayerBullet bullet : playerBullets)
            if (bullet.getPosition().equals(position))
                return true;
        return false;
    }


    public boolean isPassage() {
        return player.getPosition().equals(passage.getPosition());
    }

    public void createItem(Position position) {
        if (!isItem(position) && new Random().nextInt(3) == 0)
            switch (new Random().nextInt(4)) {
                case 0:
                    items.add(new HealingPotion(position, depth));
                    break;
                case 1:
                    items.add(new MaxHealthPotion(position, depth));
                    break;
                case 2:
                    items.add(new TimePotion(position, depth));
                    break;
                case 3:
                    items.add(new WeaponItem(position, depth));
                    break;
            }
    }
}