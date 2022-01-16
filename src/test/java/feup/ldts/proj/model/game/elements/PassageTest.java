package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Passage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageTest {
    private Passage passage;

    @BeforeEach
    public void setUp() {
        this.passage = new Passage(new Position(5, 5));
    }

}
