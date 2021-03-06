package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import org.junit.jupiter.api.Assertions;
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
    public void getPlayerCharTest() {
        Assertions.assertEquals('\u00D4', viewer.getChar(player));
    }

    @Test
    public void getPlayerColorTest() {
        Assertions.assertEquals(Game.Colors.get("HealthyGreen"), viewer.getColor(player));

        player.setHP(14);
        Assertions.assertEquals(Game.Colors.get("Green"), viewer.getColor(player));

        player.setHP(10);
        Assertions.assertEquals(Game.Colors.get("DarkGreen"), viewer.getColor(player));

        player.setHP(6);
        Assertions.assertEquals(Game.Colors.get("WoundedGreen"), viewer.getColor(player));

        player.setHP(2);
        Assertions.assertEquals(Game.Colors.get("DyingGreen"), viewer.getColor(player));
    }

    @Test
    public void drawPlayerTest() {
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(player.getPosition().getX(),player.getPosition().getY(), '\u00D4', Game.Colors.get("HealthyGreen"));

        player.setHP(14);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(player.getPosition().getX(),player.getPosition().getY(), '\u00D4', Game.Colors.get("Green"));

        player.setHP(10);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(player.getPosition().getX(),player.getPosition().getY(), '\u00D4', Game.Colors.get("DarkGreen"));

        player.setHP(6);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(player.getPosition().getX(),player.getPosition().getY(), '\u00D4', Game.Colors.get("WoundedGreen"));

        player.setHP(2);
        viewer.draw(player, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(player.getPosition().getX(),player.getPosition().getY(), '\u00D4', Game.Colors.get("DyingGreen"));
    }
}