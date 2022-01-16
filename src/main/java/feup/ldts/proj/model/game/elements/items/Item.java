package feup.ldts.proj.model.game.elements.items;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;

public abstract class Item extends Element {
    int depth;

    public Item(Position position, int depth) {
        super(position);
        this.depth = depth;
    }

    public abstract void onPickup(Player player);
}
