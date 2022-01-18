package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.controller.game.elements.strategies.attack.BiteStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.RandomMovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Skeleton;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BiteStrategyTest {
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
    }

    @Test
    public void biteStrategyTest() {
        BiteStrategy bite = Mockito.mock(BiteStrategy.class);

        Monster monster = new Zombie(new Position(7, 8), 99, bite, new RandomMovementStrategy());
        Player player = room.getPlayer();
        List<Monster> monsters = new ArrayList<>();

        monsters.add(monster);
        room.setMonsters(monsters);
        room.setObservers();

        monster.attack(player, room);
        Mockito.verify(bite, Mockito.times(1)).attack(monster, player, room);
    }
}
