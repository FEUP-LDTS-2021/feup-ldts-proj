package feup.ldts.proj.viewer.game.elements.bullets;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.viewer.game.elements.ElementViewer;

public class MonsterBulletViewer implements ElementViewer<MonsterBullet> {
    @Override
    public void draw(MonsterBullet element, GUI gui) {
        gui.drawBullet(element.getPosition(), element.getColor());
    }
}
