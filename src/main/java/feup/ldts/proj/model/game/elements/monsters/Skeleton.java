package feup.ldts.proj.model.game.elements.monsters;

import feup.ldts.proj.controller.game.elements.strategies.attack.AttackStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.MovementStrategy;
import feup.ldts.proj.model.game.Position;

public class Skeleton extends Monster {

    public Skeleton(Position position, int depth, AttackStrategy attackStrategy, MovementStrategy movementStrategy) {
        super(position, depth, attackStrategy, movementStrategy);
    }

    @Override
    public void setBasics() {
        this.baseHP = 3;
        this.baseDamage = 2;
    }

}
