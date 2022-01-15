package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.model.game.Position;

public class PlayerBullet extends Bullet {
    public PlayerBullet(Position position, int maxRange, int damage, Direction facingDirection) {
        super(position, maxRange, damage, facingDirection);
    }
}
