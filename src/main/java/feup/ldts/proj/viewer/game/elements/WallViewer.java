package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Wall;

public class WallViewer implements ElementViewer<Wall> {

    @Override
    public String getColor(Wall wall) {
        return Game.Colors.get("Blurple");
    }

    @Override
    public char getChar(Wall wall) {
        return '#';
    }

    @Override
    public void draw(Wall element, GUI gui) {
        gui.drawCharacter(element.getPosition().getX(), element.getPosition().getY(), getChar(element), getColor(element));
    }
}
