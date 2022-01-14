/*
package viewer.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.bullets.Bullet;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.viewer.elements.bullets.BulletViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BulletViewerTest {
    private Bullet bullet;
    private BulletViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        bullet = new Bullet(5, 5, 10, 10, Element.Direction.RIGHT);
        viewer = new BulletViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawBulletTest() {
        bullet.setDistanceTravelled(5);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("Golden"));

        bullet.setDistanceTravelled(9);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("SlightRust"));


        bullet.setDistanceTravelled(10);
        viewer.draw(bullet, gui);
        Mockito.verify(gui, Mockito.times(1)).drawBullet(bullet.getPosition(), Game.Colors.get("Rust"));
    }
}
*/
