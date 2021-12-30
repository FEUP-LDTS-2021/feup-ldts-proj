package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;

import java.util.Random;

public class Monster extends Element {
    final String MONSTER_COLOR = Game.Colors.get("Red");
    final int baseHP = 5;
    int HP;
    final int baseDamage = 1;
    int damage;

    public Monster(int x, int y, int depth) {
        super(x, y);
        HP = baseHP * depth;
        damage = baseDamage * depth;
    }

    public int getHP() {
        return HP;
    }

    public int getDamage() {
        return damage;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Position moveMonster() {
        return new Position(position.getX(), position.getY());
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(MONSTER_COLOR));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}

