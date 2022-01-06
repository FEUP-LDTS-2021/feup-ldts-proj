package feup.ldts.proj.viewer.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}
