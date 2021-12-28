import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Position position;
    Player player;


    @BeforeEach
    public void setUpPlayer() {
        position = new Position(5, 5);
        player = new Player(position.getX(), position.getY());
    }

    @Test
    public void testMoveRight() {
        player.movePlayerRight();
        Assertions.assertEquals(new Position(6, 5), player.getPosition());
    }

    @Test
    public void testMoveLeft() {
        player.movePlayerLeft();
        Assertions.assertEquals(new Position(4, 5), player.getPosition());
    }

    @Test
    public void testMoveUp() {
        player.movePlayerUp();
        Assertions.assertEquals(new Position(5, 4), player.getPosition());
    }

    @Test
    public void testMoveDown() {
        player.movePlayerDown();
        Assertions.assertEquals(new Position(5, 6), player.getPosition());
    }
}
