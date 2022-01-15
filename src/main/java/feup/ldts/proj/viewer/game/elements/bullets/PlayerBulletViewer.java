package feup.ldts.proj.viewer.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Wall;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.viewer.game.elements.ElementViewer;

public class PlayerBulletViewer implements ElementViewer<PlayerBullet> {

    @Override
    public String getColor(PlayerBullet bullet) {

        if (bullet.isAtLimit()) return Game.Colors.get("Rust");
        else if (bullet.isAlmostAtLimit()) return Game.Colors.get("SlightRust");
        return Game.Colors.get("Golden");
    }

    @Override
    public char getChar(PlayerBullet bullet) {
        switch (bullet.getFacingDirection()) {
            case UP:    return '*';
            case RIGHT: return '*';
            case DOWN:  return '*';
            case LEFT:  return '*';
            default:    return '*';
        }
    }


    @Override
    public void draw(PlayerBullet bullet, GUI gui) {
        gui.drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), getChar(bullet), getColor(bullet));
    }
}
