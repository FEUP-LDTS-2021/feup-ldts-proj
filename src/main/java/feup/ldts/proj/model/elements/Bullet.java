package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.observers.BulletObserver;
import feup.ldts.proj.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends Element {
    final String BULLET_COLOR_INI, BULLET_COLOR_MED, BULLET_COLOR_END;
    int maxRange, distanceTravelled, damage;
    Element.Direction facingDirection;
    List<BulletObserver> observers;

    //constructors

    public Bullet(int x, int y, int maxRange, int damage, Element.Direction facingDirection) {
        super(x, y);
        this.maxRange = maxRange;
        this.facingDirection = facingDirection;
        this.damage = damage;
        distanceTravelled = 0;
        observers = new ArrayList<BulletObserver>();

        BULLET_COLOR_INI = Game.Colors.get("Golden");
        BULLET_COLOR_MED = Game.Colors.get("SlightRust");
        BULLET_COLOR_END = Game.Colors.get("Rust");
    }

    //getters

    public Element.Direction getFacingDirection() {
        return facingDirection;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public int getDamage() {
        return damage;
    }

    public String getColor() {
        if (isAtLimit()) return BULLET_COLOR_END;
        else if (distanceTravelled == maxRange - 1) return BULLET_COLOR_MED;
        return BULLET_COLOR_INI;
    }

    public List<BulletObserver> getObservers() {return observers;}

    //setters

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    //other functions

    public boolean isAtLimit() {
        return (distanceTravelled >= maxRange);
    }

    public void incrementDistanceTravelled() {
        distanceTravelled++;
    }


    //other methods

    public void addBulletObserver(BulletObserver observer) {
        this.observers.add(observer);
    }

    //----------------------------------------------------------------------------------

    //movement related methods, will be moved to its respective controller at another time

    public Position moveBullet() {
        switch (facingDirection) {
            case UP:
                return new Position(position.getX(), position.getY() - 1);
            case RIGHT:
                return new Position(position.getX() + 1, position.getY());
            case LEFT:
                return new Position(position.getX() - 1, position.getY());
            case DOWN:
                return new Position(position.getX(), position.getY() + 1);
        }
        return new Position(position.getX(), position.getY());
    }
}