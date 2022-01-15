package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

public interface AttackStrategy {
    void attack(Monster monster, Player player, Room room);
}
