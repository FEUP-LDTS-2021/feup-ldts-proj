package feup.ldts.proj.model.game.elements;
import feup.ldts.proj.model.game.Position;


public abstract class Element {
    protected Position position;

    public static enum Direction {UP, RIGHT, DOWN, LEFT}

    public Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {this.position = position;}
}
