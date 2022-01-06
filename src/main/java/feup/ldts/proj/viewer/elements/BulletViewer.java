package feup.ldts.proj.viewer.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Bullet;

public class BulletViewer implements ElementViewer<Bullet> {
    @Override
    public void draw(Bullet element, GUI gui) {
        gui.drawBullet(element.getPosition(), element.getColor());
    }
}
