package feup.ldts.proj.controller.game.elements.strategies.attack;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

import java.util.Random;

public class ShootStrategy implements AttackStrategy {

    @Override
    public void attack(Monster monster, Player player, Room room) {
        if (new Random().nextInt(8) == 1)
            room.addMonsterBullet(monster.createMonsterBullet(room.getDepth(), getDirection(monster.getPosition(), player.getPosition())));
    }

    private Element.Direction getDirection(Position monsterPosition, Position playerPosition) {
        final int monsterX = monsterPosition.getX(), monsterY = monsterPosition.getY(),
                playerX = playerPosition.getX(), playerY = playerPosition.getY();

        //if they are in the same column
        if (monsterX == playerX) {
            //then decide whether to shoot up or down
            if (monsterY < playerY) return Element.Direction.DOWN;
            else return Element.Direction.UP;
        }
        //if they are in the same row
        if (monsterY == playerY) {
            //then decide to shoot left or right
            if (monsterX < playerX) return Element.Direction.RIGHT;
            else return Element.Direction.LEFT;
        }
        //if he's closer in the y direction, then shoot in the x direction (so as to not allow him to change rows, since we know for sure we're not going to hit him directly)
        if (Math.abs(monsterY - playerY) < Math.abs(monsterX - playerX)) {
            if (monsterX < playerX) return Element.Direction.RIGHT;
            else return Element.Direction.LEFT;
        }
        //same rationale as above but for the case that the player is closer in the x direction
        if (monsterY < playerY) return Element.Direction.DOWN;
        else return Element.Direction.UP;
    }
}
