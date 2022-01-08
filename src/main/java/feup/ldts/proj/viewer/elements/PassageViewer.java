package feup.ldts.proj.viewer.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Passage;

import javax.swing.text.View;

public class PassageViewer implements ElementViewer<Passage> {
    @Override
    public void draw(Passage element, GUI gui) {
        gui.drawPassage(element.getPosition(), element.getColor());
    }
}
