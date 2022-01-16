package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.items.Item;

public class HealingPotion extends Item {
    private int baseHealAmount;
    private int healAmount;

    public HealingPotion(Position position, int depth) {
        super(position);
        this.baseHealAmount = 1;
        this.healAmount = baseHealAmount + 2 * depth;
    }

    @Override
    public void onPickup(Player player) {
        player.healHP(healAmount);
    }
}
