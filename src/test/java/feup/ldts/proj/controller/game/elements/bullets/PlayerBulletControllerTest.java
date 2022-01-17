package feup.ldts.proj.controller.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.game.elements.bullets.PlayerBulletController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class PlayerBulletControllerTest {
    private PlayerBulletController controller;
    private Room room;
    private Game game;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        room = new RoomBuilder(99, 99).createRoom(new Player(new Position(-1, -1)));
        controller = new PlayerBulletController(room);
        game = Mockito.mock(Game.class);
    }
    
    @Test
    public void moveBulletTest() {
        int playerX = room.getPlayer().getPosition().getX();
        int playerY = room.getPlayer().getPosition().getY();
        room.addPlayerBullet(new PlayerBullet(new Position(playerX, playerY), 4, 4, Element.Direction.UP));

        controller.step(game, GUI.ACTION.UP, 300);
        Assertions.assertEquals(new Position(playerX, playerY - 1), room.getPlayerBullets().get(0).getPosition());
    }
    
    @Property(tries = 10)
    public void testBulletFade(@ForAll @IntRange(min = 1, max = 1000) int a) {
        PlayerBullet bullet = new PlayerBullet(new Position(10, 10), 5, 1, Element.Direction.UP);
        for (int i = 0; i < a; i++) bullet.setPosition(bullet.moveBullet());

        if (a >= bullet.getMaxRange()) Assertions.assertTrue(bullet.isAtLimit());
        else Assertions.assertFalse(bullet.isAtLimit());
    }
}
