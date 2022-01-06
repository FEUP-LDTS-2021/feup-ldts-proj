import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.elements.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class RoomTest {
    private int depthNum, roomNum;
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        depthNum = 0;
        roomNum = 1;
        room = new Room(Game.constructRoomFileURI(), roomNum);
    }

    @Test
    public void numMonstersTest() {
        Assertions.assertEquals(4, room.getMonsters().size()); //The number of monsters is correct
    }

    @Test
    public void numWallsTest() {
        Assertions.assertEquals(96, room.getWalls().size()); //The number of walls is correct
    }

    @Test
    public void gatePositionTest() {
        Assertions.assertEquals(new Position(9, 15), room.getGate()); // The gate (hole) spawned at the correct location
    }

    @Test
    public void collisionTest() {
        Player player = room.getPlayer();

        for (Monster monster : room.getMonsters())
            Assertions.assertFalse(room.canExecuteMovement(monster.moveMonster()));

        Assertions.assertFalse(room.canExecuteMovement(player.getPosition().getLeft()));
        Assertions.assertFalse(room.canExecuteMovement(player.getPosition().getRight()));
        Assertions.assertFalse(room.canExecuteMovement(player.getPosition().getUp()));
        Assertions.assertFalse(room.canExecuteMovement(player.getPosition().getDown()));
    }
}