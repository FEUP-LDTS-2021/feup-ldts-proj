package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Weapon;


public class Player extends Element {
    private final String PLAYER_COLOR_20, PLAYER_COLOR_40, PLAYER_COLOR_60, PLAYER_COLOR_80, PLAYER_COLOR_100;
    private int HP, maxHP;
    private Weapon weapon;
    private Element.Direction facingDirection;

    //constructors

    public Player(int x, int y) {
        super(x, y);
        this.maxHP = 10;
        this.weapon = new Weapon(2, 5, 3);
        this.facingDirection = Element.Direction.DOWN;
        HP = maxHP;

        PLAYER_COLOR_20 = Game.Colors.get("DyingGreen");
        PLAYER_COLOR_40 = Game.Colors.get("WoundedGreen");
        PLAYER_COLOR_60 = Game.Colors.get("DarkGreen");
        PLAYER_COLOR_80 = Game.Colors.get("Green");
        PLAYER_COLOR_100 = Game.Colors.get("HealthyGreen");
    }

    public Player(int x, int y, int maxHP, int HP, Weapon weapon) {
        super(x, y);
        this.HP = HP;
        this.weapon = weapon;
        this.facingDirection = Element.Direction.DOWN;
        this.maxHP = maxHP;

        PLAYER_COLOR_20 = Game.Colors.get("DyingGreen");
        PLAYER_COLOR_40 = Game.Colors.get("WoundedGreen");
        PLAYER_COLOR_60 = Game.Colors.get("DarkGreen");
        PLAYER_COLOR_80 = Game.Colors.get("Green");
        PLAYER_COLOR_100 = Game.Colors.get("HealthyGreen");
    }

    //getters

    public String getColor() {
        if ((float) HP/maxHP <= 0.20) return PLAYER_COLOR_20;
        if ((float) HP/maxHP <= 0.40) return PLAYER_COLOR_40;
        if ((float) HP/maxHP <= 0.60) return PLAYER_COLOR_60;
        if ((float) HP/maxHP <= 0.80) return PLAYER_COLOR_80;
        return PLAYER_COLOR_100;
    }

    public int getHP() {
        return HP;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Element.Direction getFacingDirection() {return facingDirection;}

    public int getMaxHP() {return maxHP;}

    //setters

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setFacingDirection(Element.Direction newFacingDirection) {this.facingDirection = newFacingDirection;}

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    //other functions

    public void decreaseHP(int damageAmount) {
        HP -= damageAmount;
    }

    //----------------------------------------------------------------------------------

    //movement related methods, will be moved to its respective controller at another time

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

    public Bullet createBullet() {
        return new Bullet(position.getX(), position.getY(), weapon.getRange(), weapon.getDamage(), facingDirection);
    }

}
