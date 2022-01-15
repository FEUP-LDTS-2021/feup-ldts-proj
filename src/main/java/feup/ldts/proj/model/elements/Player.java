package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.observers.MonsterObserver;
import feup.ldts.proj.model.Weapon;
import feup.ldts.proj.model.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.elements.monsters.Monster;

import java.util.List;


public class Player extends Element {
    private final String PLAYER_COLOR_20, PLAYER_COLOR_40, PLAYER_COLOR_60, PLAYER_COLOR_80, PLAYER_COLOR_100;
    private int HP, maxHP;
    private Weapon weapon;
    private Element.Direction facingDirection;
    private long timeLeft;

    //--------------------------------------constructor--------------------------------------

    public Player(int x, int y) {
        super(x, y);
        this.maxHP = 10;
        this.weapon = new Weapon(50, 10, 10);
        this.facingDirection = Element.Direction.DOWN;
        HP = maxHP;

        PLAYER_COLOR_20 = Game.Colors.get("DyingGreen");
        PLAYER_COLOR_40 = Game.Colors.get("WoundedGreen");
        PLAYER_COLOR_60 = Game.Colors.get("DarkGreen");
        PLAYER_COLOR_80 = Game.Colors.get("Green");
        PLAYER_COLOR_100 = Game.Colors.get("HealthyGreen");
        timeLeft = 120;
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
        timeLeft = 120;
    }

    //----------------------------------------getters-----------------------------------------

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

    public long getTimeLeft() { return timeLeft; }

    //----------------------------------------setters-----------------------------------------

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

    public void setObservers(List<Monster> monsters) {
        for (Monster monster : monsters) {
            monster.addMonsterObserver(new MonsterObserver() {
                @Override
                public void hpChanged(Monster monster) {
                    //do nothing
                }

                @Override
                public void positionChanged(Monster monster) {
                    if (monster.getPosition().equals(position))
                        decreaseHP(monster.getDamage());
                }
            });
        }
    }

    //-------------------------------------other functions-------------------------------------

    public void decreaseHP(int damageAmount) {
        HP = Math.max(0, HP - damageAmount);
    }

    public void decreaseTime() {timeLeft--;}

    public void resetTime() {timeLeft = 120;}

    public void healHP(int healAmount) {
        HP = Math.min(maxHP, HP + healAmount);
    }

    public PlayerBullet createBullet() {
        return new PlayerBullet(position.getX(), position.getY(), weapon.getRange(), weapon.getDamage(), facingDirection);
    }
}
