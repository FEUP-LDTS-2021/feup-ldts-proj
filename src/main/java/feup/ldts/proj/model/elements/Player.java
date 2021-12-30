package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Weapon;
import org.w3c.dom.Text;

import java.io.IOException;

public class Player extends Element {
    private int hp;
    private Weapon weapon;

    public Player(int x, int y) {
        super(x, y);
        this.hp = 10;
        this.weapon = new Weapon(2, 3);
    }

    public int getHp() {
        return hp;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void movePlayerLeft() {
        movePlayer(position.getLeft());
    }

    public void movePlayerRight() {
        movePlayer(position.getRight());
    }

    public void movePlayerUp() {
        movePlayer(position.getUp());
    }

    public void movePlayerDown() {
        movePlayer(position.getDown());
    }

    public void movePlayer(Position position) {
        setPosition(position);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString("#017727"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
