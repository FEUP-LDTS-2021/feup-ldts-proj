package feup.ldts.proj.viewer.elements;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public void draw(Player element, GUI gui) {
        gui.drawPlayer(element.getPosition(), element.getColor());
    }
}
