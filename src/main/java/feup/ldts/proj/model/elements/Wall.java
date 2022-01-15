package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;

public class Wall extends Element {
    final String WALL_COLOR;
    public Wall(int x, int y) {
        super(x, y);
        WALL_COLOR = Game.Colors.get("DarkGray");
    }

    public String getColor() {
        return WALL_COLOR;
    }
}
