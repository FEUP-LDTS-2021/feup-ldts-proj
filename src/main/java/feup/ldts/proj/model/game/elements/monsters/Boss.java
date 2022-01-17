package feup.ldts.proj.model.game.elements.monsters;

import feup.ldts.proj.controller.game.elements.strategies.AttackStrategy;
import feup.ldts.proj.controller.game.elements.strategies.MovementStrategy;
import feup.ldts.proj.model.game.Position;

public class Boss extends Monster {

    public Boss(Position position, int depth, AttackStrategy attackStrategy, MovementStrategy movementStrategy) {
        super(position, depth, attackStrategy, movementStrategy);
    }

    @Override
    protected void setBasics() {
        baseHP = 30;
        baseDamage = 5;
    }
}
