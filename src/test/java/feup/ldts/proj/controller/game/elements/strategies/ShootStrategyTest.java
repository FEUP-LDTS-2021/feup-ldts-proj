package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.controller.game.elements.strategies.attack.ShootStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.RandomMovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Skeleton;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ShootStrategyTest {
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
    }

    @Test
    public void shootStrategyTest() {
        ShootStrategy shootStrategy = Mockito.mock(ShootStrategy.class);

        Monster monster = new Skeleton(new Position(6, 8), 99, shootStrategy, new RandomMovementStrategy());
        Player player = room.getPlayer();
        List<Monster> monsters = new ArrayList<>();

        monsters.add(monster);
        room.setMonsters(monsters);
        room.setObservers();

        room.addMonsterBullet(monster.createMonsterBullet(99, Element.Direction.RIGHT));
        monster.attack(player, room);

        Mockito.verify(shootStrategy, Mockito.times(1)).attack(monster, player, room);
    }
}
