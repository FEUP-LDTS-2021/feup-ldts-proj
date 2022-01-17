package feup.ldts.proj.controller.observers;

import feup.ldts.proj.controller.game.elements.strategies.attack.BiteStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.RandomMovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class PlayerObserverTest {
    private Player player;
    private Zombie zombie;
    private List<Monster> monsters = new ArrayList<Monster>();

    @BeforeEach
    public void setUp() {
        player = new Player(new Position(1, 1));
        zombie = new Zombie(new Position(0, 1), 1, new BiteStrategy(), new RandomMovementStrategy());
        monsters.add(zombie);
    }

    /*@Test
    public void alertPositionChangedTest() {
        int currentHP = player.getHP();
        zombie.setPosition(new Position(1, 1));
        Assertions.assertTrue(player.getHP() < currentHP);
    }*/
}
