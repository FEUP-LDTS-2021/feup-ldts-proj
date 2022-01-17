package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Passage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PassageViewerTest {
    private Passage passage;
    private PassageViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.passage = new Passage(new Position(1, 1));
        viewer = new PassageViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void getPassageCharTest() {
        Assertions.assertEquals('O', viewer.getChar(passage));
    }

    @Test
    public void getPassageColorTest() {
        Assertions.assertEquals(Game.Colors.get("Black"), viewer.getColor(passage));
    }

    @Test
    public void drawPassageTest() {
        viewer.draw(passage, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1, 'O', Game.Colors.get("Black"));
    }
}
