package feup.ldts.proj.model.game.elements.items.potions;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.items.Item;

public abstract class Potion extends Item {
    public Potion(Position position, int depth) {
        super(position, depth);
    }

    public abstract void applyEffect(Player player);
}
