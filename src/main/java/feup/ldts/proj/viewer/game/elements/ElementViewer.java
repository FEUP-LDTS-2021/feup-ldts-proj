package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Element;

public interface ElementViewer<T extends Element> {
    String getColor(T element);

    char getChar(T element);

    void draw(T element, GUI gui);
}
