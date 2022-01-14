package feup.ldts.proj.model.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.elements.bullets.Bullet;

public class PlayerBullet extends Bullet {
    public PlayerBullet(int x, int y, int maxRange, int damage, Direction facingDirection) {
        super(x, y, maxRange, damage, facingDirection);
        BULLET_COLOR_INI = Game.Colors.get("Golden");
        BULLET_COLOR_MED = Game.Colors.get("SlightRust");
        BULLET_COLOR_END = Game.Colors.get("Rust");
    }
}
