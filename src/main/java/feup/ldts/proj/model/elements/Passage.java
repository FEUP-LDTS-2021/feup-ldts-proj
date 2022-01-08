package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;

public class Passage extends Element {
    private final String color;

    public Passage(int x, int y) {
        super(x, y);
        color = Game.Colors.get("Black");
    }

    @Override
    public String getColor() {
        return color;
    }
}
