package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;

public class HealingPotion extends Potion {
    private int baseHealAmount;
    private int healAmount;

    public HealingPotion(Position position, int depth) {
        super(position, depth);
        this.baseHealAmount = 1;
        this.healAmount = baseHealAmount + 2 * depth;
    }

    @Override
    public void onPickup(Player player) {
        applyEffect(player);
    }

    @Override
    public void applyEffect(Player player) {
        player.healHP(healAmount);
    }
}
