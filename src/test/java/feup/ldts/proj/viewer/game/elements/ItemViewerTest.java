package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.items.Item;
import feup.ldts.proj.model.game.elements.items.WeaponItem;
import feup.ldts.proj.model.game.elements.items.potions.HealingPotion;
import feup.ldts.proj.model.game.elements.items.potions.MaxHealthPotion;
import feup.ldts.proj.model.game.elements.items.potions.TimePotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ItemViewerTest {
    private Item item;
    private ItemViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        viewer = new ItemViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void maxHealthPotionTest() {
        item = new MaxHealthPotion(new Position(1, 1), 1);
        Assertions.assertEquals('$', viewer.getChar(item));
        Assertions.assertEquals(Game.Colors.get("LightGreen"), viewer.getColor(item));

        viewer.draw(item, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, '$', Game.Colors.get("LightGreen"));
    }

    @Test
    public void healingPotionTest() {
        item = new HealingPotion(new Position(1, 1), 1);
        Assertions.assertEquals('$', viewer.getChar(item));
        Assertions.assertEquals(Game.Colors.get("Red"), viewer.getColor(item));

        viewer.draw(item, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, '$', Game.Colors.get("Red"));
    }

    @Test
    public void timePotionTest() {
        item = new TimePotion(new Position(1, 1), 1);
        Assertions.assertEquals('\u00A4', viewer.getChar(item));
        Assertions.assertEquals(Game.Colors.get("LightBlue"), viewer.getColor(item));

        viewer.draw(item, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, '\u00A4', Game.Colors.get("LightBlue"));
    }

    @Test
    public void weaponItemTest() {
        item = new WeaponItem(new Position(1, 1), 1);
        Assertions.assertEquals('&', viewer.getChar(item));
        Assertions.assertEquals(Game.Colors.get("Pink"), viewer.getColor(item));

        viewer.draw(item, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, '&', Game.Colors.get("Pink"));
    }
}
