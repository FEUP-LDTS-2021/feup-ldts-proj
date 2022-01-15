package viewer.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.viewer.elements.bullets.MonsterBulletViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterBulletViewerTest {
    private MonsterBullet bullet;
    private MonsterBulletViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        bullet = new MonsterBullet(5, 5, 4, 4, Element.Direction.UP);
        viewer = new MonsterBulletViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawMonsterBulletTest() {
        bullet.setDistanceTravelled(1);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("EvilPurple"));

        bullet.setDistanceTravelled(3);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("VanishingPurple"));

        bullet.setDistanceTravelled(4);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("GonePurple"));
    }


}
