package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;

public class MaxHealthPotion extends Potion {
    private int baseHealthBoost;
    private int healthBoost;


    public MaxHealthPotion (Position position, int depth) {
        super(position, depth);
        this.baseHealthBoost = 2;
        this.healthBoost = baseHealthBoost + 2*depth;
    }

    @Override
    public void onPickup(Player player) {
        applyEffect(player);
    }

    @Override
    public void applyEffect(Player player) {
        player.increaseMaxHP(baseHealthBoost);
    }
}
