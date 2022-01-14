package feup.ldts.proj.controller.elements.strategies;

import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.room.Room;

public interface AttackStrategy {
    void attack(Monster monster, Player player, Room room);
}
