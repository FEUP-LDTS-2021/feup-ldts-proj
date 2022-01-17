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

public class VictoryMenuController extends Controller<Menu> {
    public VictoryMenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {

        switch (action) {
            case LEFT:
                getModel().previousOption();
                break;
            case RIGHT:
                getModel().nextOption();
                break;
            case SELECT:
                while (!Game.stateStack.empty())
                    Game.stateStack.pop();
                if (getModel().isSelected(0)) Game.stateStack.push(new MainMenuState(new MainMenu()));
                if (getModel().isSelected(1)) game.setState(null);
                break;
            case RETURN:
                while (!Game.stateStack.empty())
                    Game.stateStack.pop();
                Game.stateStack.push(new MainMenuState(new MainMenu()));
                break;
            case EXIT:
                game.setState(null);
        }
    }
}
