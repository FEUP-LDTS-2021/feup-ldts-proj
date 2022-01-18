package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.bullets.Bullet;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BulletTest {
    private Bullet bullet;

    @BeforeEach
    public void setUp() {
        this.bullet = new PlayerBullet(new Position(7, 9), 3, 5, Element.Direction.LEFT);
    }

    @Test
    public void moveBulletTest() {
        Assertions.assertEquals(new Position(6, 9), bullet.moveBullet());
    }

    @Test
    public void bulletLimitsTest() {
        bullet.setPosition(bullet.moveBullet());
        Assertions.assertFalse(bullet.isAlmostAtLimit());

        bullet.setPosition(bullet.moveBullet());
        Assertions.assertTrue(bullet.isAlmostAtLimit());

        bullet.setPosition(bullet.moveBullet());
        Assertions.assertTrue(bullet.isAtLimit());
    }

    @Test
    public void incrementDistanceTest() {
        Assertions.assertEquals(0, bullet.getDistanceTravelled());

        bullet.setPosition(bullet.moveBullet());
        Assertions.assertEquals(1, bullet.getDistanceTravelled());

        bullet.setPosition(bullet.moveBullet());
        Assertions.assertEquals(2, bullet.getDistanceTravelled());

        bullet.setPosition(bullet.moveBullet());
        Assertions.assertEquals(3, bullet.getDistanceTravelled());
    }
}
