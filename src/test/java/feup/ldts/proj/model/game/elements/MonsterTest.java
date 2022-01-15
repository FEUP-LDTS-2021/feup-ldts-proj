package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.controller.game.elements.strategies.BiteStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    private Monster monster;

    @BeforeEach
    public void setUp() {
        this.monster = new Zombie(new Position(1, 1), 1, new BiteStrategy());
    }

    @Test
    public void decreaseHPTest() {
        monster.decreaseHP(4);
        Assertions.assertEquals(1, monster.getHP());

        monster.decreaseHP(100);
        Assertions.assertEquals(0, monster.getHP());
    }
}
