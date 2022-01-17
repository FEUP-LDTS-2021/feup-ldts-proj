package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.game.elements.strategies.attack.BiteStrategy;
import feup.ldts.proj.controller.game.elements.strategies.movement.RandomMovementStrategy;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterViewerTest {
    private Monster monster;
    private MonsterViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        monster = new Zombie(new Position(1, 1), 1, new BiteStrategy(), new RandomMovementStrategy());
        viewer = new MonsterViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void getMonsterCharTest() {
        Assertions.assertEquals('"', viewer.getChar(monster));
    }

    @Test
    public void getMonsterColorTest() {
        Assertions.assertEquals(Game.Colors.get("Red"), viewer.getColor(monster));

        monster.setHP(3);
        Assertions.assertEquals(Game.Colors.get("Pink"), viewer.getColor(monster));

        monster.setHP(1);
        Assertions.assertEquals(Game.Colors.get("Purple"), viewer.getColor(monster));
    }

    @Test
    public void drawMonsterTest() {
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1,'"' ,Game.Colors.get("Red"));

        monster.setHP(3);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1,'"' ,Game.Colors.get("Pink"));

        monster.setHP(1);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawCharacter(1, 1,'"' ,Game.Colors.get("Purple"));
    }
}
