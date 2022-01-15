package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

public class MonsterBullet extends Bullet {
    public MonsterBullet(Position position, int maxRange, int damage, Direction facingDirection) {
        super(position, maxRange, damage, facingDirection);
        BULLET_COLOR_INI = Game.Colors.get("EvilPurple");
        BULLET_COLOR_MED = Game.Colors.get("VanishingPurple");
        BULLET_COLOR_END = Game.Colors.get("GonePurple");
    }
}
