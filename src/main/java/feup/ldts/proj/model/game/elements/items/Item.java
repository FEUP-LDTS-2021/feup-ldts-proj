package feup.ldts.proj.model.game.elements.items;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;

public abstract class Item extends Element {

    public Item(Position position) {
        super(position);
    }

    public abstract void onPickup(Player player);
}
