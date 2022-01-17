package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.model.menu.MainMenu;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.states.GameState;
import feup.ldts.proj.states.MainMenuState;

import java.io.IOException;
import java.net.URISyntaxException;

public class ControlsMenuController extends Controller<Menu> {
    public ControlsMenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        switch (action) {
            case RETURN:
            case SELECT:
                Game.stateStack.push(new MainMenuState(new MainMenu()));
                break;
            case EXIT:
                game.setState(null);
        }
    }
}
