package feup.ldts.proj.model.game.elements.monsters;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.game.elements.strategies.AttackStrategy;
import feup.ldts.proj.model.game.Position;

public class Skeleton extends Monster {

    public Skeleton(Position position, int depth, AttackStrategy attackStrategy) {
        super(position, depth, attackStrategy);
    }

    @Override
    public char getChar() {
        return '\'';
    }

    @Override
    public void setBasics() {
        this.baseHP = 3;
        this.baseDamage = 2;
    }

    @Override
    protected void setColors() {
        MONSTER_COLOR_33 = Game.Colors.get("Purple");
        MONSTER_COLOR_66 = Game.Colors.get("Pink");
        MONSTER_COLOR_100 = Game.Colors.get("Red");
    }
}
