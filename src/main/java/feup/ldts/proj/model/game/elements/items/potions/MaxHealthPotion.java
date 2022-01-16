package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.items.Item;

public class MaxHealthPotion extends Item {
    private int baseHealthBoost;
    private int healthBoost;


    public MaxHealthPotion (Position position, int depth) {
        super(position);
        this.baseHealthBoost = 2;
        this.healthBoost = baseHealthBoost + 2*depth;
    }

    @Override
    public void onPickup(Player player) {
        player.increaseMaxHP(baseHealthBoost);
    }
}
