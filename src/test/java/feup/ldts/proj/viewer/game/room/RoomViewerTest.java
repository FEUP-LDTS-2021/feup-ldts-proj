package feup.ldts.proj.viewer.game.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RoomViewerTest {
    private GUI gui;
    private RoomViewer roomViewer;
    private Room room;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
        gui = Mockito.mock(GUI.class);
        roomViewer = new RoomViewer(gui);

    }

    @Test
    void drawTest() throws IOException {
        roomViewer.draw(room);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(8, 8, 'X',  Game.Colors.get("HealthyGreen"));
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(2, 2, '"', Game.Colors.get("Red"));
    }
}
