package feup.ldts.proj.viewer.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Bullet;

public class BulletViewer implements ElementViewer<Bullet> {
    final String //to be changed
            BULLET_COLOR_INI = Game.Colors.get("Golden"),
            BULLET_COLOR_MED = Game.Colors.get("SlightRust"),
            BULLET_COLOR_END = Game.Colors.get("Rust");
    @Override
    public void draw(Bullet element, GUI gui) {
        if (element.isAtLimit())
            gui.drawBullet(element.getPosition(), BULLET_COLOR_END);
        else if (element.getDistanceTravelled() == element.getMaxRange() - 1)
            gui.drawBullet(element.getPosition(), BULLET_COLOR_MED);
        else
            gui.drawBullet(element.getPosition(), BULLET_COLOR_INI);
    }
}
