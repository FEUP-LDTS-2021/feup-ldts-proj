package feup.ldts.proj.controller.elements;

import feup.ldts.proj.controller.elements.PlayerController;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.net.URISyntaxException;

public class PlayerControllerTest {
    private PlayerController playerController;
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        this.room = new RoomBuilder(99, 99).createRoom(new Player(-1, -1));
        this.playerController = new PlayerController(room);
    }

    @Test
    public void playerMovementTest() {
        playerController.movePlayerRight();
        Assertions.assertEquals(new Position(8, 8), room.getPlayer().getPosition()); //can't move
        playerController.movePlayerUp();
        Assertions.assertEquals(new Position(8, 7), room.getPlayer().getPosition());
        playerController.movePlayerLeft();
        Assertions.assertEquals(new Position(7, 7), room.getPlayer().getPosition());
        playerController.movePlayerDown();
        Assertions.assertEquals(new Position(7, 8), room.getPlayer().getPosition());
        playerController.movePlayerRight();
        Assertions.assertEquals(new Position(8, 8), room.getPlayer().getPosition());
    }

}