package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;

public class MonsterViewer implements ElementViewer<Monster> {
    @Override
    public String getColor(Monster element) {
        return null;
    }

    @Override
    public char getChar(Monster element) {
        if (element instanceof Zombie) return '"';
        return '\'';
    }

    @Override
    public void draw(Monster element, GUI gui) {
        gui.drawCharacter(element.getPosition().getX(), element.getPosition().getY(), getChar(element), getColor(element));
    }
}
