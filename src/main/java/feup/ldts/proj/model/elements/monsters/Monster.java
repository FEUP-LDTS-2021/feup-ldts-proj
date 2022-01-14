package feup.ldts.proj.model.elements.monsters;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.elements.strategies.AttackStrategy;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.room.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Element {
    protected String MONSTER_COLOR_100, MONSTER_COLOR_66, MONSTER_COLOR_33;
    protected List<MonsterObserver> observers;
    protected int baseHP;
    protected int baseDamage;
    final AttackStrategy attackStrategy;
    int HP, maxHP;
    int damage;

    //constructors

    public Monster(int x, int y, int depth, AttackStrategy attackStrategy) {
        super(x, y);
        setBasics();
        setColors();
        this.maxHP = baseHP * depth;
        this.HP = maxHP;
        this.damage = baseDamage * depth;
        this.attackStrategy = attackStrategy;

        observers = new ArrayList<MonsterObserver>();
    }

    //getters

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public String getColor() {
        if ((float) HP/maxHP <= 0.33) return MONSTER_COLOR_33;
        if ((float) HP/maxHP <= 0.66) return MONSTER_COLOR_66;
        return MONSTER_COLOR_100;
    }

    public int getMaxHP() { return maxHP; }

    public abstract char getChar();

    //setters

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setDamage(int damage) { this.damage = damage; }

    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        for (MonsterObserver observer : observers)
            observer.positionChanged(this);
    }

    protected abstract void setBasics();

    protected abstract void setColors();

    //other functions

    public void decreaseHP(int damageAmount) {
        HP = Math.max(0, HP - damageAmount);
        for (MonsterObserver observer : observers)
            observer.hpChanged(this);
    }

    public void addMonsterObserver(MonsterObserver observer) {
        this.observers.add(observer);
    }

    public void bite(Player player) {
        player.decreaseHP(damage);
    }

    public MonsterBullet createMonsterBullet(int depth, Element.Direction direction) {
        int maxRange = 3 + depth * 2;
        return new MonsterBullet(position.getX(), position.getY(), maxRange, damage, direction);
    }

    public void attack(Player player, Room room) {
        this.attackStrategy.attack(this, player, room);
    }

}

