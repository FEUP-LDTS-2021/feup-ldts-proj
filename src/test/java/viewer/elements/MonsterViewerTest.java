package viewer.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.viewer.elements.MonsterViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterViewerTest {
    private Monster monster;
    private MonsterViewer viewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {
        monster = new Monster(1, 1, 1);
        viewer = new MonsterViewer();
        gui = Mockito.mock(GUI.class);
    }

    @Test
    public void drawMonsterTest() {
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Red"));

        monster.setHP(3);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Pink"));

        monster.setHP(1);
        viewer.draw(monster, gui);
        Mockito.verify(gui, Mockito.times(1)).drawMonster(new Position(1, 1), Game.Colors.get("Purple"));
    }
}
