package viewer.room;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.*;
import feup.ldts.proj.model.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.viewer.room.RoomViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;

public class RoomViewerTest {
    private GUI gui;
    private RoomViewer roomViewer;
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1);
        gui = Mockito.mock(GUI.class);
        roomViewer = new RoomViewer(gui);

        room.setWalls(Arrays.asList(new Wall(1,1), new Wall(1,2), new Wall(2,1), new Wall(2, 2)));
        room.setPlayer(new Player(10, 10));
        room.setMonsters(Arrays.asList(new Monster(10, 9, 1), new Monster(10, 11, 1), new Monster(9, 10, 1), new Monster(11, 10, 1)));
        room.setPlayerBullets(Arrays.asList(new PlayerBullet(8, 8, 4, 5, Element.Direction.UP)));
    }

    @Test
    void drawTest() throws IOException {
        roomViewer.draw(room);
        Mockito.verify(gui, Mockito.times(4)).drawWall(Mockito.any(Position.class));
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(Mockito.any(Position.class), Mockito.any(String.class));
        Mockito.verify(gui, Mockito.times(4)).drawMonster(Mockito.any(Position.class), Mockito.any(String.class));
    }

}
