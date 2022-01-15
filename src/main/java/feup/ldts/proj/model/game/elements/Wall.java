package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

public class Wall extends Element {
    final String WALL_COLOR;
    public Wall(Position position) {
        super(position);
        WALL_COLOR = Game.Colors.get("Blurple");
    }

    public String getColor() {
        return WALL_COLOR;
    }
}
