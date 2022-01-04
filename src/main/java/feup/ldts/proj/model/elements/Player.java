package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Weapon;
import org.w3c.dom.Text;

import java.io.IOException;

public class Player extends Element {
    
    final String PLAYER_COLOR = Game.Colors.get("DarkGreen");
    private int HP;
    private Weapon weapon;
    private Game.Direction facingDirection;
    
    public Player(int x, int y) {
        super(x, y);
        this.HP = 10;
        this.weapon = new Weapon(2, 5, 5);
        this.facingDirection = Game.Direction.DOWN;
    }

    //getters

    public int getHP() {
        return HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Game.Direction getFacingDirection() {return facingDirection;}

    //setters

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setFacingDirection(Game.Direction newFacingDirection) {this.facingDirection = newFacingDirection;}

    //movement

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

    //health related methods

    public void decreaseHP(int damageAmount) {
        HP = Math.max(HP - damageAmount, 0);
    }

    //combat related methods

    public Bullet createBullet() {
        return new Bullet(position.getX(), position.getY(), weapon.getRange(), weapon.getDamage(), facingDirection);
    }

    @Override
    public void draw(TextGraphics graphics) throws IOException {
        graphics.setForegroundColor(TextColor.Factory.fromString(PLAYER_COLOR));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
}
