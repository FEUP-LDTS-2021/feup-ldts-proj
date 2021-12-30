import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.Room;
import feup.ldts.proj.model.elements.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class RoomTest {
    private int depthNum, roomNum;
    private Player player;
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        depthNum = 0;
        roomNum = 1;
        room = new Room(depthNum, roomNum, player);
    }

    @Test
    public void numMonstersTest() {
        Assertions.assertEquals(4, room.getMonsters().size()); //The number of monsters is correct
    }

    @Test
    public void numWallsTest() {
        Assertions.assertEquals(164, room.getWalls().size()); //The number of walls is correct
    }

    @Test
    public void gatePositionTest() {
        Assertions.assertEquals(new Position(12, 12), room.getGate()); // The gate (hole) spawned at the correct location
    }

    @Test
    public void playerPositionTest() {
        Assertions.assertEquals(new Position(10, 10), room.getPlayer().getPosition());
    }
}
