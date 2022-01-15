package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {
    @Override
    public void draw(Wall element, GUI gui) {
        gui.drawWall(element.getPosition());
    }
}
