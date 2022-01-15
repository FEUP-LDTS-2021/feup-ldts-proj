package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(Player element, GUI gui) {
        gui.drawPlayer(element.getPosition(), element.getColor());
    }
}