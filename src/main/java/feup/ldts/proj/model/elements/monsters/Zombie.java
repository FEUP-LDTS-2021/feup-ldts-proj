package feup.ldts.proj.model.elements.monsters;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.strategies.AttackStrategy;

public class Zombie extends Monster {

    public Zombie(int x, int y, int depth, AttackStrategy attackStrategy) {
        super(x, y, depth, attackStrategy);
    }

    @Override
    public char getChar() {
        return '\"';
    }

    @Override
    public void setBasics() {
        this.baseHP = 5;
        this.baseDamage = 1;
    }

    @Override
    protected void setColors() {
        MONSTER_COLOR_33 = Game.Colors.get("Purple");
        MONSTER_COLOR_66 = Game.Colors.get("Pink");
        MONSTER_COLOR_100 = Game.Colors.get("Red");
    }
}
