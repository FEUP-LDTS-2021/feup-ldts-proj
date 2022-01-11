package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.observers.MonsterObserver;
import feup.ldts.proj.model.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Monster extends Element {
    final String
            MONSTER_COLOR_100,
            MONSTER_COLOR_66,
            MONSTER_COLOR_33;
    List<MonsterObserver> observers;
    final int baseHP = 5;
    final int baseDamage = 1;
    int HP, maxHP;
    int damage;

    //constructors

    public Monster(int x, int y, int depth) {
        super(x, y);
        maxHP = baseHP * depth;
        HP = maxHP;
        damage = baseDamage * depth;

        MONSTER_COLOR_33 = Game.Colors.get("Purple");
        MONSTER_COLOR_66 = Game.Colors.get("Pink");
        MONSTER_COLOR_100 = Game.Colors.get("Red");
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

    //other functions

    public void decreaseHP(int damageAmount) {
        HP = Math.max(0, HP - damageAmount);
        for (MonsterObserver observer : observers)
            observer.hpChanged(this);
    }

    public void addMonsterObserver(MonsterObserver observer) {
        this.observers.add(observer);
    }


    //----------------------------------------------------------------------------------

    //movement related methods, will be moved to its respective controller

    public Position moveMonster() {
        switch (new Random().nextInt(4)) {
            case 0: return new Position(position.getX() - 1, position.getY());
            case 1: return new Position(position.getX() + 1, position.getY());
            case 2: return new Position(position.getX(), position.getY() - 1);
            case 3: return new Position(position.getX(), position.getY() + 1);
        }
        return new Position(position.getX(), position.getY());
    }
}

