package feup.ldts.proj.model.game.elements;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        this.player = new Player(new Position(5, 5));
    }

    @Test
    public void getColorTest() {
        Assertions.assertEquals("#02f751", player.getColor());

        player.setHP(7);
        Assertions.assertEquals("#19b33a", player.getColor());

        player.setHP(5);
        Assertions.assertEquals("#1d871a", player.getColor());

        player.setHP(3);
        Assertions.assertEquals("#1a610e", player.getColor());

        player.setHP(1);
        Assertions.assertEquals("#103d02", player.getColor());
    }

    @Test
    public void decreaseHPTest() {
        player.decreaseHP(1);
        Assertions.assertEquals(9, player.getHP());

        player.decreaseHP(500);
        Assertions.assertEquals(0, player.getHP());
    }

    @Test
    public void decreaseTimeTest() {
        player.decreaseTime();
        Assertions.assertEquals(119, player.getTimeLeft());
    }

    @Test
    public void resetTimeTest() {
        player.decreaseTime();
        player.decreaseTime();
        player.decreaseTime();
        player.decreaseTime();
        Assertions.assertEquals(116, player.getTimeLeft());

        player.resetTime();
        Assertions.assertEquals(120, player.getTimeLeft());
    }

    @Test
    public void healHPTest() {
        player.decreaseHP(5);
        Assertions.assertEquals(5, player.getHP());

        player.healHP(100);
        Assertions.assertEquals(player.getMaxHP(), player.getHP());
    }
}