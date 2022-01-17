package feup.ldts.proj.gui;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
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
    public void drawTextTest() {
        GUI.drawText(position, "Example Text", Game.Colors.get("Black"));
        Mockito.verify(textGraphics, Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString(Game.Colors.get("Black")));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(position.getX(), position.getY(), "Example Text");
    }
}
