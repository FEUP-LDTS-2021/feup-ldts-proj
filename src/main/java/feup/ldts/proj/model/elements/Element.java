package feup.ldts.proj.model.elements;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.model.Position;

import java.io.IOException;
import java.util.HashMap;

public abstract class Element {
    protected Position position;

    public static enum Direction {UP, RIGHT, DOWN, LEFT}

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract String getColor();
}
