import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class RoomTest {
    private int depth, roomNum;
    private RoomBuilder builder;
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        this.depth = 99;
        this.roomNum = 99;
        this.builder = new RoomBuilder(depth, roomNum);
        this.room = builder.createRoom(new Player(10, 10));
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
