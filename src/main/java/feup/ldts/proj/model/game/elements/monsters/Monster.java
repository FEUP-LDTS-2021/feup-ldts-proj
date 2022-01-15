package feup.ldts.proj.model.game.elements.monsters;

import feup.ldts.proj.controller.game.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.game.elements.strategies.AttackStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.room.Room;

import java.util.ArrayList;
import java.util.List;

public abstract class Monster extends Element {
    protected List<MonsterObserver> observers;
    protected int baseHP;
    protected int baseDamage;
    final AttackStrategy attackStrategy;
    int HP, maxHP;
    int damage;

    //--------------------------------------constructor--------------------------------------

    public Monster(Position position, int depth, AttackStrategy attackStrategy) {
        super(position);
        setBasics();
        this.maxHP = baseHP * depth;
        this.HP = maxHP;
        this.damage = baseDamage * depth;
        this.attackStrategy = attackStrategy;
        this.observers = new ArrayList<MonsterObserver>();
    }

    //----------------------------------------getters-----------------------------------------

    public int getHP() {
        return HP;
    }

    public int getMaxHP() { return maxHP; }

    //----------------------------------------setters-----------------------------------------

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        for (MonsterObserver observer : observers)
            observer.positionChanged(this);
    }

    protected abstract void setBasics();

    //-------------------------------------other functions-------------------------------------


    public void addMonsterObserver(MonsterObserver observer) {
        this.observers.add(observer);
    }

    public void bite(Player player) {
        player.decreaseHP(damage);
    }

    public void attack(Player player, Room room) {
        this.attackStrategy.attack(this, player, room);
    }

    public MonsterBullet createMonsterBullet(int depth, Element.Direction direction) {
        int maxRange = 3 + depth * 2;
        return new MonsterBullet(position, maxRange, damage, direction);
    }

    public void decreaseHP(int damageAmount) {
        HP = Math.max(0, HP - damageAmount);
        for (MonsterObserver observer : observers)
            observer.hpChanged(this);
    }
}

