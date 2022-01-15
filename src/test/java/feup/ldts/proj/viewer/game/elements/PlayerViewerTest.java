package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.viewer.game.elements.PlayerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewerTest {
    private Player player;
    private PlayerViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        this.player = new Player(new Position(5, 5));
        viewer = new PlayerViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawPlayerTest() {
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(), Game.Colors.get("HealthyGreen"));

        player.setHP(7);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(), Game.Colors.get("Green"));

        player.setHP(5);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(), Game.Colors.get("DarkGreen"));

        player.setHP(3);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(), Game.Colors.get("WoundedGreen"));

        player.setHP(1);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawPlayer(player.getPosition(), Game.Colors.get("DyingGreen"));
    }
}