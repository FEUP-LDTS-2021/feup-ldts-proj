package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WallTest {
    private Wall wall;

    @BeforeEach
    public void setUp() {
        this.wall = new Wall(new Position(5, 5));
    }

}
