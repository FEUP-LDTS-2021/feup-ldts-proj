package feup.ldts.proj.model.elements;

import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Weapon;

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

    }

    public void movePlayerRight() {

    }

    public void movePlayerUp() {

    }

    public void movePlayerDown() {

    }

    public void movePlayer(Position position) {

    }
}
