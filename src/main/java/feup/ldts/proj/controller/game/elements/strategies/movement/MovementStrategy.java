package feup.ldts.proj.controller.game.elements.strategies.movement;

import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

public interface MovementStrategy {
    void move(Monster monster, Room room);
}
