package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.controller.game.elements.observers.BulletObserver;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Bullet extends Element {
    protected String BULLET_COLOR_INI, BULLET_COLOR_MED, BULLET_COLOR_END;
    protected Element.Direction facingDirection;
    protected List<BulletObserver> observers;
    protected int maxRange, distanceTravelled, damage;

    //--------------------------------------constructor--------------------------------------

    public Bullet(Position position, int maxRange, int damage, Element.Direction facingDirection) {
        super(position);
        this.maxRange = maxRange;
        this.facingDirection = facingDirection;
        this.damage = damage;
        this.distanceTravelled = 0;
        observers = new ArrayList<BulletObserver>();
    }

    //----------------------------------------getters-----------------------------------------

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

    public List<BulletObserver> getObservers() {return observers;}


    public String getColor() {
        if (isAtLimit()) return BULLET_COLOR_END;
        else if (distanceTravelled == maxRange - 1) return BULLET_COLOR_MED;
        return BULLET_COLOR_INI;
    }

    //----------------------------------------setters-----------------------------------------

    public void setDistanceTravelled(int distanceTravelled) { this.distanceTravelled = distanceTravelled; }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        incrementDistanceTravelled();
    }

    //-------------------------------------other functions-------------------------------------

    public boolean isAtLimit() {
        return distanceTravelled >= maxRange;
    }

    public void incrementDistanceTravelled() {
        distanceTravelled++;
    }

    public void addBulletObserver(BulletObserver observer) {
        this.observers.add(observer);
    }

    public void alertObserversDecayed() {
        for (BulletObserver observer : observers)
            observer.decayed(this);
    }

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