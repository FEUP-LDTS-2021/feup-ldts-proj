package controller.observers;

import feup.ldts.proj.controller.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.elements.strategies.BiteStrategy;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.elements.monsters.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayerObserverTest {
    private Player player;
    private Zombie zombie;
    private List<Monster> monsters = new ArrayList<Monster>();

    @BeforeEach
    public void setUp() {
        player = new Player(1, 1);
        zombie = new Zombie(0, 1, 1, new BiteStrategy());
        monsters.add(zombie);
        player.setObservers(monsters);
    }

    @Test
    public void alertPositionChangedTest() {
        int currentHP = player.getHP();
        zombie.setPosition(new Position(1, 1));
        Assertions.assertTrue(player.getHP() < currentHP);
    }
}
