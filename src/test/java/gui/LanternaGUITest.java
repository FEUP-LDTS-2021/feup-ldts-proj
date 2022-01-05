package gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.gui.LanternaGUI;
import feup.ldts.proj.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LanternaGUITest {
    private Screen screen;
    private LanternaGUI GUI;
    private TextGraphics textGraphics;
    private Position position;

    @BeforeEach
    public void setUp() {
        textGraphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        GUI = new LanternaGUI(screen);
        position = new Position(5, 5);
    }

    @Test
    public void drawPlayerTest() {
        GUI.drawPlayer(position, LanternaGUI.Colors.get("HealthyGreen"));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("HealthyGreen")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "X");
    }

    @Test
    public void drawWallTest() {
        GUI.drawWall(position);
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("Blurple")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "#");
    }

    @Test
    public void drawMonsterTest() {
        GUI.drawMonster(position, LanternaGUI.Colors.get("Red"));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("Red")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "M");
    }

    @Test
    public void drawBulletTest() {
        GUI.drawBullet(position, LanternaGUI.Colors.get("Golden"));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("Golden")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "*");
    }

    @Test
    public void drawTextTest() {
        GUI.drawText(position, "Example Text", LanternaGUI.Colors.get("Black"));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("Black")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "Example Text");
    }

}
