package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;

public class Monster extends Element {
    final int baseHP = 5;
    int HP;

    public Monster(int x, int y, int depth) {
        super(x, y);
        HP = baseHP * depth;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString(Game.Colors.get("Red")));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }
}

