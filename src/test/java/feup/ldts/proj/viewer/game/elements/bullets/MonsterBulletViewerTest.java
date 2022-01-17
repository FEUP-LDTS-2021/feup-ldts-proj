package feup.ldts.proj.viewer.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterBulletViewerTest {
    private MonsterBullet bullet;
    private MonsterBulletViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        bullet = new MonsterBullet(new Position(5, 5), 4, 4, Element.Direction.UP);
        viewer = new MonsterBulletViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void getMonsterBulletCharTest() {
        Assertions.assertEquals('*', viewer.getChar(bullet));
    }

    @Test
    public void getMonsterBulletColor() {
        bullet.setDistanceTravelled(1);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("EvilPurple"), viewer.getColor(bullet));

        bullet.setDistanceTravelled(3);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("VanishingPurple"), viewer.getColor(bullet));

        bullet.setDistanceTravelled(4);
        viewer.draw(bullet, gui);
        Assertions.assertEquals(Game.Colors.get("GonePurple"), viewer.getColor(bullet));
    }

    @Test
    public void drawMonsterBulletTest() {
        bullet.setDistanceTravelled(1);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '*', Game.Colors.get("EvilPurple"));

        bullet.setDistanceTravelled(3);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '*', Game.Colors.get("VanishingPurple"));

        bullet.setDistanceTravelled(4);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(bullet.getPosition().getX(), bullet.getPosition().getY(), '*', Game.Colors.get("GonePurple"));
    }
}
