package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.game.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.game.elements.observers.PlayerObserver;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.game.elements.monsters.Monster;

import java.util.ArrayList;
import java.util.List;


public class Player extends Element {
    private int HP, maxHP;
    private Weapon weapon;
    private Element.Direction facingDirection;
    private long timeLeft;
    protected List<PlayerObserver> observers;

    //--------------------------------------constructor--------------------------------------

    public Player(Position position) {
        super(position);
        this.maxHP = 10;
        this.weapon = new Weapon(50, 10, 10);
        this.facingDirection = Element.Direction.DOWN;
        HP = maxHP;
        timeLeft = 120;
        this.observers = new ArrayList<PlayerObserver>();
    }

    public Player(Position position, int maxHP, int HP, Weapon weapon) {
        super(position);
        this.HP = HP;
        this.weapon = weapon;
        this.facingDirection = Element.Direction.DOWN;
        this.maxHP = maxHP;
        this.timeLeft = 120;
        this.observers = new ArrayList<PlayerObserver>();
    }

    //----------------------------------------getters-----------------------------------------

    public int getHP() {
        return HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getMaxHP() {return maxHP;}

    public long getTimeLeft() { return timeLeft; }

    public List<PlayerObserver> getObservers() { return observers; }

    //----------------------------------------setters-----------------------------------------

    @Override
    public void setPosition(Position position) {
        this.position = position;
        alertObserversMoved();
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setFacingDirection(Element.Direction newFacingDirection) {this.facingDirection = newFacingDirection;}

    //-------------------------------------other functions-------------------------------------

    public void decreaseHP(int damageAmount) {
        HP = Math.max(0, HP - damageAmount);
    }

    public void decreaseTime() {timeLeft--;}

    public void resetTime() {timeLeft = 120;}

    public void healHP(int healAmount) {
        HP = Math.min(maxHP, HP + healAmount);
    }

    public void increaseMaxHP(int healthBoost) { maxHP += healthBoost; }

    public void increaseTime(int timeAmount) { timeLeft += timeAmount; }

    public PlayerBullet createBullet() {
        return new PlayerBullet(position, weapon.getRange(), weapon.getDamage(), facingDirection);
    }

    public void addPlayerObserver(PlayerObserver observer) {
        this.observers.add(observer);
    }

    public void alertObserversMoved() {
        for (PlayerObserver observer : observers)
            observer.positionChanged(this);
    }
}
