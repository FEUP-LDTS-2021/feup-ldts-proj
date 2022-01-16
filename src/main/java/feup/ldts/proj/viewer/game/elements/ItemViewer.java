package feup.ldts.proj.viewer.game.elements;


import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.items.Item;
import feup.ldts.proj.model.game.elements.items.potions.HealingPotion;
import feup.ldts.proj.model.game.elements.items.potions.MaxHealthPotion;
import feup.ldts.proj.model.game.elements.items.potions.TimePotion;

public class ItemViewer implements ElementViewer<Item> {

    @Override
    public String getColor(Item item) {
        if (item instanceof HealingPotion) return Game.Colors.get("Red");
        if (item instanceof MaxHealthPotion) return Game.Colors.get("LightGreen");
        if (item instanceof TimePotion) return Game.Colors.get("LightBlue");
        return "";
    }

    @Override
    public char getChar(Item item) {
        if (item instanceof HealingPotion) return 'H';
        if (item instanceof MaxHealthPotion) return 'H';
        if (item instanceof TimePotion) return 'T';
        return 'p';
    }

    @Override
    public void draw(Item item, GUI gui) {
        gui.drawCharacter(item.getPosition().getX(), item.getPosition().getY(), getChar(item), getColor(item));
    }
}
