package feup.ldts.proj.model;

import feup.ldts.proj.model.elements.Passage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassageTest {
    private Passage passage;

    @BeforeEach
    public void setUp() {
        this.passage = new Passage(5, 5);
    }

    @Test
    public void getColorTest() {
        Assertions.assertEquals("#000000", passage.getColor());
    }
}
