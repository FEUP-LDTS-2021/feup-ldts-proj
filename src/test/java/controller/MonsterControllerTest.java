package controller;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.MonsterController;
import feup.ldts.proj.controller.elements.PlayerController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MonsterControllerTest {
    private MonsterController monsterController;
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException, URISyntaxException {
        this.room = new RoomBuilder(99, 99).createRoom(new Player(-1, -1));
        this.monsterController = new MonsterController(room);
    }



    @Test
    public void monsterMovementTest() throws IOException {
        monsterController.step(Mockito.mock(Game.class), GUI.ACTION.UP, 600);
        Assertions.assertNotEquals(new Position(2, 2), monsterController.getModel().getMonsters().get(0).getPosition());
    }
}
