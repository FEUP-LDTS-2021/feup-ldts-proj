package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Passage;

public class PassageViewer implements ElementViewer<Passage> {
    @Override
    public void draw(Passage element, GUI gui) {
        gui.drawPassage(element.getPosition(), element.getColor());
    }
}
