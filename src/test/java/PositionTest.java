import feup.ldts.proj.model.Position;
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
}
