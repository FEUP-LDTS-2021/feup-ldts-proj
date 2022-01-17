package feup.ldts.proj.controller.game.elements.strategies.movement;

import feup.ldts.proj.controller.game.elements.strategies.movement.MovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

public class RandomMovementStrategy implements MovementStrategy {
    @Override
    public void move(Monster monster, Room room) {
        Position newMonsterPos = monster.getPosition().getRandomPosition();
        if (!room.isWall(newMonsterPos) && !room.isMonster(newMonsterPos))
            monster.setPosition(newMonsterPos);
    }


}
