package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

public class PlayerBullet extends Bullet {
    public PlayerBullet(Position position, int maxRange, int damage, Direction facingDirection) {
        super(position, maxRange, damage, facingDirection);
        BULLET_COLOR_INI = Game.Colors.get("Golden");
        BULLET_COLOR_MED = Game.Colors.get("SlightRust");
        BULLET_COLOR_END = Game.Colors.get("Rust");
    }
}
