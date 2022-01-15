package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.game.elements.strategies.BiteStrategy;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import feup.ldts.proj.viewer.game.elements.MonsterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterViewerTest {
    private Monster monster;
    private MonsterViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        monster = new Zombie(new Position(1, 1), 1, new BiteStrategy());
        viewer = new MonsterViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawMonsterTest() {
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Red"), monster.getChar());

        monster.setHP(3);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Pink"), monster.getChar());

        monster.setHP(1);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Purple"), monster.getChar());
    }
}
