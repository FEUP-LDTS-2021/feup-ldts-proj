package feup.ldts.proj.controller.game.elements.observers;

import feup.ldts.proj.controller.game.elements.strategies.attack.BiteStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.RandomMovementStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlayerObserverTest {
    private Room room;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
    }

    @Test
    public void playerObserverTest() {
        List<Monster> monsters = room.getMonsters();
        Monster monster = monsters.get(0);
        Player player = room.getPlayer();
        PlayerObserver observer = Mockito.mock(PlayerObserver.class);
        player.addPlayerObserver(observer);

        monster.setPosition(player.getPosition());
        Assertions.assertNotEquals(player.getMaxHP(), player.getHP());

        player.setPosition(new Position(2, 2));
        Mockito.verify(observer, Mockito.times(1)).positionChanged(player);
    }
}
