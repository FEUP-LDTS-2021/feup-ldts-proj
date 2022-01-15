package feup.ldts.proj.viewer.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.viewer.game.elements.ElementViewer;

public class MonsterBulletViewer implements ElementViewer<MonsterBullet> {

    @Override
    public String getColor(MonsterBullet bullet) {

        if (bullet.isAtLimit()) return Game.Colors.get("GonePurple");
        else if (bullet.isAlmostAtLimit()) return Game.Colors.get("VanishingPurple");
        return Game.Colors.get("EvilPurple");
    }

    @Override
    public char getChar(MonsterBullet bullet) {
        switch (bullet.getFacingDirection()) {
            case UP:    return '*';
            case RIGHT: return '*';
            case DOWN:  return '*';
            case LEFT:  return '*';
            default:    return '*';
        }
    }


    @Override
    public void draw(MonsterBullet bullet, GUI gui) {
        gui.drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), getChar(bullet), getColor(bullet));
    }
}
