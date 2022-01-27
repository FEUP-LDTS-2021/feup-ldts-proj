package feup.ldts.proj.controller.game.elements;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
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
        this.room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
        this.playerController = new PlayerController(room);
    }

    @Test
    public void playerMovementTest() {
        playerController.movePlayerRight();
        Assertions.assertEquals(new Position(8, 8), room.getPlayer().getPosition());

        playerController.movePlayerUp();
        Assertions.assertEquals(new Position(8, 7), room.getPlayer().getPosition());

        playerController.movePlayerLeft();
        Assertions.assertEquals(new Position(7, 7), room.getPlayer().getPosition());

        playerController.movePlayerDown();
        Assertions.assertEquals(new Position(7, 8), room.getPlayer().getPosition());

        playerController.movePlayerRight();
        Assertions.assertEquals(new Position(8, 8), room.getPlayer().getPosition());
    }

    @Test
    public void playerShootTest() {
        playerController.shoot();
        Assertions.assertFalse(room.getPlayerBullets().isEmpty());
    }
}