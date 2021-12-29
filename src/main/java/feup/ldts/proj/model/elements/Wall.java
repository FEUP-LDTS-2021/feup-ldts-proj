package feup.ldts.proj.model.elements;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.model.Position;

import java.io.IOException;

public class Wall extends Element {
    public Wall(int x, int y) {
        super(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(Screen screen) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#C9F4DA"));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#5DCA5F"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "#");
    }
}
