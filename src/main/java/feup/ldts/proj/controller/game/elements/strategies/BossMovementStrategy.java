package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

public class BossMovementStrategy implements MovementStrategy {
    @Override
    public void move(Monster boss, Room room) {
        Position newBossPosition = nearestPosition(boss.getPosition(), room.getPlayer().getPosition());
        if (!room.isWall(newBossPosition) && !room.isMonster(newBossPosition))
            boss.setPosition(newBossPosition);
    }

    public Position nearestPosition(Position bossPosition, Position playerPosition) {
        int xDiff = bossPosition.getX() - playerPosition.getX(); // if positive then the player is to the left of the boss
        int yDiff = bossPosition.getY() - playerPosition.getY(); // if positive then the player is above the boss

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (xDiff > 0) return bossPosition.getLeft();
            else return bossPosition.getRight();
        } else {
            if (yDiff > 0) return bossPosition.getUp();
            else return bossPosition.getDown();
        }
    }
}
