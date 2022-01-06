package viewer.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.viewer.elements.PlayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewerTest {
    private Player player;
    private PlayerViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.player = new Player(5, 5);
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawPlayerTest() {
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(5, 5), Game.Colors.get("HealthyGreen"));

        player.setHP(8);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(5, 5), Game.Colors.get("Green"));

        player.setHP(6);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(5, 5), Game.Colors.get("DarkGreen"));

        player.setHP(4);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(5, 5), Game.Colors.get("WoundedGreen"));

        player.setHP(2);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(5, 5), Game.Colors.get("DyingGreen"));
    }
}
