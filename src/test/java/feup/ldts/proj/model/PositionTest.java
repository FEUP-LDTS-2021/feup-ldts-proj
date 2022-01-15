package feup.ldts.proj.model;

import feup.ldts.proj.model.game.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setUpPosition() {
        position = new Position(5, 4);
    }

    @Test
    public void getXTest() {
        Assertions.assertEquals(5, position.getX());
    }

    @Test
    public void getYTest() {
        Assertions.assertEquals(4, position.getY());
    }

    @Test
    public void getLeftTest() {
        Assertions.assertEquals(new Position(4, 4), position.getLeft());
    }

    @Test
    public void getRightTest() {
        Assertions.assertEquals(new Position(6, 4), position.getRight());
    }

    @Test
    public void getUpTest() {
        Assertions.assertEquals(new Position(5, 3), position.getUp());
    }

    @Test
    public void getDownTest() {
        Assertions.assertEquals(new Position(5, 5), position.getDown());
    }

    @Test
    public void equalsOperatorTest() {
        Assertions.assertEquals(new Position(5, 4), this.position);
    }

    @Test
    public void getRandomTest() {
        Position rand = position.getRandomPosition();
        Assertions.assertNotEquals(rand, this.position);
    }

    @Test
    public void positionToStringTest() {
        String strPos = position.toString();
        Assertions.assertEquals("X: " + position.getX() + " Y: " + position.getY(), strPos);
    }

    @Test
    public void distanceTest() {
        Assertions.assertEquals(1, position.distanceTo(new Position(5, 3)));
    }
}
