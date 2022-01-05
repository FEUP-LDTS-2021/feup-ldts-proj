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

    @BeforeEach
    public void setUp() {
        textGraphics = Mockito.mock(TextGraphics.class);
        screen = Mockito.mock(Screen.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        GUI = new LanternaGUI(screen);
    }

    @Test
    public void drawPlayer() {

    }

    @Test
    public void drawWall() {
        GUI.drawWall(new Position(5, 5));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(LanternaGUI.Colors.get("Blurple")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(5, 5, "#");
    }

    @Test
    public void drawMonster() {

    }

    @Test
    public void drawBullet() {

    }

}
