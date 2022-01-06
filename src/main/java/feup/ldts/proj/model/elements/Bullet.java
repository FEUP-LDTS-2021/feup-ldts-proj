package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;

import java.io.IOException;

public class Bullet extends Element {
    final String
            BULLET_COLOR_INI = Game.Colors.get("Golden"),
            BULLET_COLOR_MED = Game.Colors.get("SlightRust"),
            BULLET_COLOR_END = Game.Colors.get("Rust");
    int maxRange, distanceTravelled, damage;
    Element.Direction facingDirection;

    public Bullet(int x, int y, int maxRange, int damage, Element.Direction facingDirection) {
        super(x, y);
        this.maxRange = maxRange;
        this.facingDirection = facingDirection;
        this.damage = damage;
        distanceTravelled = 0;
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

    //setters

    public void setDistanceTravelled(int distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }

    //movement related methods

    public boolean isAtLimit() {
        return (distanceTravelled >= maxRange);
    }

    public void incrementDistanceTravelled() {
        distanceTravelled++;
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