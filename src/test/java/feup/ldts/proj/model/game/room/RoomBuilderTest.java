package feup.ldts.proj.model.game.room;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class RoomBuilderTest {
    private int depth, roomNum;
    private RoomBuilder builder;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.depth = 99;
        this.roomNum = 99;
        this.builder = new RoomBuilder(depth, roomNum);
    }

    @Test
    public void numMonstersTest() {
        Assertions.assertEquals(1, builder.createMonsters().size());
    }

    @Test
    public void numWallsTest() {
        Assertions.assertEquals(84, builder.createWalls().size());
    }

    @Test
    public void passagePositionTest() {
        Assertions.assertEquals(new Position(9, 15), builder.createPassage().getPosition());
    }

    @Test
    public void playerPositionTest() {
        Player player = new Player(new Position(-1, -1));
        builder.setPlayerPosition(player);
        Assertions.assertEquals(new Position(8, 8), player.getPosition());
    }
}
