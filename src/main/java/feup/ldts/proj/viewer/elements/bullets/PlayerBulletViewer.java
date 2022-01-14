package feup.ldts.proj.viewer.elements.bullets;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.bullets.PlayerBullet;
import feup.ldts.proj.viewer.elements.ElementViewer;

public class PlayerBulletViewer implements ElementViewer<PlayerBullet> {
    @Override
    public void draw(PlayerBullet element, GUI gui) {
        gui.drawBullet(element.getPosition(), element.getColor());
    }
}
