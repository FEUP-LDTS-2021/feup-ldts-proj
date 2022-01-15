package controller.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.elements.bullets.MonsterBulletController;
import feup.ldts.proj.controller.elements.bullets.PlayerBulletController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

public class MonsterBulletControllerTest {
    private MonsterBulletController controller;
    private Room room;
    private Game game;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException, FontFormatException {
        room = new RoomBuilder(99, 99).createRoom(new Player(-1, -1));
        controller = new MonsterBulletController(room);
        game = Mockito.mock(Game.class);
    }

    @Test
    public void moveBulletTest() {
        int monsterX = room.getMonsters().get(0).getPosition().getX();
        int monsterY = room.getMonsters().get(0).getPosition().getY();

        room.addMonsterBullet(new MonsterBullet(monsterX, monsterY, 4, 4, Element.Direction.LEFT));
        controller.step(game, GUI.ACTION.LEFT, 300);
        Assertions.assertEquals(new Position(monsterX - 1, monsterY), room.getMonsterBullets().get(0).getPosition());
    }

     /*@Property(tries = 20)
    public void testBulletFade(@ForAll int a) {
        MonsterBullet bullet = new MonsterBullet(10, 10, 5, 1, Element.Direction.UP);
        for (int i = 0; i < a; i++) {
            bullet.setPosition(bullet.moveBullet());
        }
        if (a >= bullet.getMaxRange())
            Assertions.assertEquals(true, bullet.isAtLimit());
        else
            Assertions.assertEquals(false, bullet.isAtLimit());
    }*/
}