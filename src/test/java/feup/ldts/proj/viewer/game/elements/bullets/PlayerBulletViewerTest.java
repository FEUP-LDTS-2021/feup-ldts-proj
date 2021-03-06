package feup.ldts.proj.viewer.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerBulletViewerTest {
    private PlayerBullet bullet;
    private PlayerBulletViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        bullet = new PlayerBullet(new Position(5, 5), 4, 4, Element.Direction.UP);
        viewer = new PlayerBulletViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void getPlayerBulletCharTest() {
        Assertions.assertEquals('\u00E3', viewer.getChar(bullet));
    }

    @Test
    public void getPlayerBulletColorTest() {
        bullet.setDistanceTravelled(1);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("Golden"), viewer.getColor(bullet));

        bullet.setDistanceTravelled(3);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("SlightRust"), viewer.getColor(bullet));

        bullet.setDistanceTravelled(4);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("Rust"), viewer.getColor(bullet));
    }

    @Test
    public void drawPlayerBulletTest() {
        bullet.setDistanceTravelled(1);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '\u00E3', Game.Colors.get("Golden"));

        bullet.setDistanceTravelled(3);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '\u00E3', Game.Colors.get("SlightRust"));

        bullet.setDistanceTravelled(4);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '\u00E3', Game.Colors.get("Rust"));
    }
}
