package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;

import java.util.Random;

public class Monster extends Element {
    final String
            MONSTER_COLOR_100 = Game.Colors.get("Red"),
            MONSTER_COLOR_66 = Game.Colors.get("Pink"),
            MONSTER_COLOR_33 = Game.Colors.get("Purple");
    final int baseHP = 5;
    final int baseDamage = 1;
    int HP, maxHP;
    int damage;

    public Monster(int x, int y, int depth) {
        super(x, y);
        maxHP = baseHP * depth;
        HP = maxHP;
        damage = baseDamage * depth;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public int getMaxHP() { return maxHP; }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setDamage(int damage) { this.damage = damage; }

    public void setMaxHP(int maxHP) { this.maxHP = maxHP; }

    public Position moveMonster() {
        switch (new Random().nextInt(4)) {
            case 0: return new Position(position.getX() - 1, position.getY());
            case 1: return new Position(position.getX() + 1, position.getY());
            case 2: return new Position(position.getX(), position.getY() - 1);
            case 3: return new Position(position.getX(), position.getY() + 1);
        }
        return new Position(position.getX(), position.getY());
    }

    public void decreaseHP(int damageAmount) {
        HP -= damageAmount;
    }
}

