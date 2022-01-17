package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.controller.game.elements.observers.BulletObserver;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;

import java.util.ArrayList;
import java.util.List;

public abstract class Bullet extends Element {
    protected Element.Direction facingDirection;
    protected List<BulletObserver> observers;
    protected int maxRange, distanceTravelled, damage;

    public Bullet(Position position, int maxRange, int damage, Element.Direction facingDirection) {
        super(position);
        this.maxRange = maxRange;
        this.facingDirection = facingDirection;
        this.damage = damage;
        this.distanceTravelled = 0;
        this.observers = new ArrayList<BulletObserver>();
    }

    public int getDamage() {
        return damage;
    }

    public Element.Direction getFacingDirection() { return facingDirection; }

    public void setDistanceTravelled(int distanceTravelled) { this.distanceTravelled = distanceTravelled; }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        incrementDistanceTravelled();
    }

    public void incrementDistanceTravelled() {
        distanceTravelled++;
    }

    public void addBulletObserver(BulletObserver observer) {
        this.observers.add(observer);
    }

    public void alertObserversDecayed() {
        for (BulletObserver observer : observers) observer.decayed(this);
    }

    public boolean isAtLimit() {
        return distanceTravelled >= maxRange;
    }

    public boolean isAlmostAtLimit() { return distanceTravelled == maxRange-1; }

    public int getMaxRange() { return maxRange; }

    public Position moveBullet() {
        switch (facingDirection) {
            case UP: return new Position(position.getX(), position.getY() - 1);
            case RIGHT: return new Position(position.getX() + 1, position.getY());
            case LEFT: return new Position(position.getX() - 1, position.getY());
            case DOWN: return new Position(position.getX(), position.getY() + 1);
            default: return new Position(position.getX(), position.getY());
        }
    }
}