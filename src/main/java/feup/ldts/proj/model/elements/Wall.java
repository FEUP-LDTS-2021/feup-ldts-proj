package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.Game;
import feup.ldts.proj.model.Position;

import java.io.IOException;

public class Wall extends Element {
    final String WALL_COLOR = Game.Colors.get("DarkGray");
    public Wall(int x, int y) {
        super(x, y);
    }

    public String getColor() {
        return WALL_COLOR;
    }
}
