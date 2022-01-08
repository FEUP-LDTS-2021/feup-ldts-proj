package feup.ldts.proj.model.elements;

import feup.ldts.proj.Game;

public class Passage extends Element {
    private final String PASSAGE_COLOR;

    public Passage(int x, int y) {
        super(x, y);
        PASSAGE_COLOR = Game.Colors.get("Black");
    }

    @Override
    public String getColor() {
        return PASSAGE_COLOR;
    }
}
