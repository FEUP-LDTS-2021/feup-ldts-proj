package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.monsters.Monster;

public class MonsterViewer implements ElementViewer<Monster> {
    @Override
    public void draw(Monster element, GUI gui) {
        gui.drawMonster(element.getPosition(), element.getColor(), element.getChar());
    }
}
