package feup.ldts.proj.viewer.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.viewer.game.room.RoomViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class RoomViewerTest {
    private GUI gui;
    private RoomViewer roomViewer;
    private Room room;

    @BeforeEach
    void setUp() throws FileNotFoundException, URISyntaxException {
        room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
        gui = Mockito.mock(GUI.class);
        roomViewer = new RoomViewer(gui);
    }

    @Test
    void drawTest() throws IOException {
        roomViewer.draw(room);
        Mockito.verify(gui, Mockito.times(84)).drawCharacter(Mockito.any(Integer.class), Mockito.any(Integer.class), '#', Game.Colors.get("Blurple"));
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(Mockito.any(Integer.class), Mockito.any(Integer.class), 'X',  Game.Colors.get("HealthyGreen"));
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(Mockito.any(Integer.class), Mockito.any(Integer.class), '"', Game.Colors.get("Red"));
    }
}
