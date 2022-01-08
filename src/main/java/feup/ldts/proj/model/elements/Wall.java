package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;

public class Wall extends Element {
    final String color;
    public Wall(int x, int y) {
        super(x, y);
        color = Game.Colors.get("DarkGray");
    }

    public String getColor() {
        return color;
    }
}
