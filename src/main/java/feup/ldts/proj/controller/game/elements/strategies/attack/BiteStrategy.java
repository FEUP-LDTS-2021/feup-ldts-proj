package feup.ldts.proj.controller.game.elements.strategies.attack;

import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

public class BiteStrategy implements AttackStrategy {
    @Override
    public void attack(Monster monster, Player player, Room room) {
        if (player.getPosition().distanceTo(monster.getPosition()) <= 1) //from all 9 squares around the player
            monster.bite(player);
    }
}
