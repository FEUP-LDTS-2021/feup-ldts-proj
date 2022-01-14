package feup.ldts.proj.controller.elements.strategies;

import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.room.Room;

public class BiteStrategy implements AttackStrategy {
    @Override
    public void attack(Monster monster, Player player, Room room) {
        if (player.getPosition().distanceTo(monster.getPosition()) <= 1) //from all 9 squares around the player
            monster.bite(player);
    }
}
