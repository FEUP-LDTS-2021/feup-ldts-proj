package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WallViewerTest {
    private Wall wall;
    private WallViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.wall = new Wall(new Position(1, 1));
        viewer = new WallViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void getWallCharTest() {
        Assertions.assertEquals('#', viewer.getChar(wall));
    }

    @Test
    public void getWallColorTest() {
        Assertions.assertEquals(Game.Colors.get("Blurple"), viewer.getColor(wall));
    }

    @Test
    public void drawWallTest() {
        viewer.draw(wall, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, '#', Game.Colors.get("Blurple"));
    }
}
