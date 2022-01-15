package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

public class Passage extends Element {
    private final String PASSAGE_COLOR;

    public Passage(Position position) {
        super(position);
        PASSAGE_COLOR = Game.Colors.get("Black");
    }

    @Override
    public String getColor() {
        return PASSAGE_COLOR;
    }
}
