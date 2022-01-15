package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Passage;

public class PassageViewer implements ElementViewer<Passage> {
    @Override
    public String getColor(Passage element) {
        return Game.Colors.get("Black");
    }

    @Override
    public char getChar(Passage element) {
        return 'O';
    }

    @Override
    public void draw(Passage element, GUI gui) {
        gui.drawCharacter(element.getPosition().getX(), element.getPosition().getY(), getChar(element), getColor(element));
    }
}
