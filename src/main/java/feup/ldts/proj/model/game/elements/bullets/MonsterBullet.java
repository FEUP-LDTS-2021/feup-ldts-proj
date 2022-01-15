package feup.ldts.proj.model.game.elements.bullets;

import feup.ldts.proj.model.game.Position;

public class MonsterBullet extends Bullet {
    public MonsterBullet(Position position, int maxRange, int damage, Direction facingDirection) {
        super(position, maxRange, damage, facingDirection);
    }
}
