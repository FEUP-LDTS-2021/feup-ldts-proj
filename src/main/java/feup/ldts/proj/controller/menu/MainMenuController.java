package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.MainMenu;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.states.GameState;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuController extends Controller<Menu> {
    public MainMenuController(Menu menu) {
        super(menu);
    }
    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
                getModel().nextOption();
                break;
            case SELECT:
                if (getModel().isSelected(0)) Game.stateStack.push(new GameState(new RoomBuilder(0, 1).createRoom(new Player(new Position(0, 0)))));
                if (getModel().isSelected(2)) game.setState(null);
                break;
            case RETURN:
            case EXIT:
                game.setState(null);
        }
    }
}
