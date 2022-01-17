package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuControllerTest {
    private Controller<Menu> controller;

    @BeforeEach
    public void setUp() {
        Game.stateStack.clear();
    }

    @Test
    public void controlsMenuSelectTest() throws IOException, URISyntaxException {
        controller = new ControlsMenuController(new ControlsMenu());

        int beforeSelectSize = Game.stateStack.size();
        controller.step(Mockito.mock(Game.class), GUI.ACTION.SELECT, 500);
        int afterSelectSize = Game.stateStack.size();

        Assertions.assertEquals(afterSelectSize, beforeSelectSize + 1);
    }

    @Test
    public void mainMenuSelectTest() throws IOException, URISyntaxException {
        controller = new MainMenuController(new MainMenu());

        int beforeSelectSize = Game.stateStack.size();
        controller.step(Mockito.mock(Game.class), GUI.ACTION.SELECT, 500);
        int afterSelectSize = Game.stateStack.size();

        Assertions.assertEquals(afterSelectSize, beforeSelectSize + 1);
    }

    @Test
    public void victoryMenuSelectTest() throws IOException, URISyntaxException {
        controller = new VictoryMenuController(new VictoryMenu());

        int beforeSelectSize = Game.stateStack.size();
        controller.step(Mockito.mock(Game.class), GUI.ACTION.SELECT, 500);
        int afterSelectSize = Game.stateStack.size();

        Assertions.assertEquals(afterSelectSize, beforeSelectSize + 1);
    }
}
