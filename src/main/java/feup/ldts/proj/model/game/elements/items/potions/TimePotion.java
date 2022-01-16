package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.items.Item;

public class TimePotion extends Potion {
    private int baseTimeAmount;
    private int timeAmount;

    public TimePotion(Position position, int depth) {
        super(position, depth);
        this.baseTimeAmount = 6;
        this.timeAmount = baseTimeAmount + 4*depth;
    }

    @Override
    public void onPickup(Player player) {
        applyEffect(player);
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseTime(timeAmount);
    }
}
