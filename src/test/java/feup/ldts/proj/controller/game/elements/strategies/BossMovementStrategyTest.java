package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.controller.game.elements.strategies.attack.BossAttackStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.BossMovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Boss;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BossMovementStrategyTest {
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.room = new RoomBuilder(3, 1).createRoom(new Player(new Position(-1, -1)));

    }

    @Test
    public void nearestPositionTest() {
        BossMovementStrategy strategy = new BossMovementStrategy();
        Assertions.assertEquals(strategy.nearestPosition(room.getBoss().getPosition(), room.getPlayer().getPosition()), room.getBoss().getPosition().getDown());
    }

    @Test
    public void moveBossTest() {
        BossMovementStrategy strategy = Mockito.mock(BossMovementStrategy.class);
        Boss boss = new Boss(new Position(4, 4), 3, new BossAttackStrategy(), strategy);

        List<Monster> monsters = new ArrayList<>();
        Player player = new Player(new Position(2, 4));
        monsters.add(boss);
        room.setMonsters(monsters);
        room.setPlayer(player);
        room.setObservers();

        strategy.move(boss, room);
        Mockito.verify(strategy, Mockito.times(1)).move(boss, room);
    }
}
