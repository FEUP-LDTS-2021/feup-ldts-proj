package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.elements.Player;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.model.room.RoomBuilder;
import feup.ldts.proj.states.GameState;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
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
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedStart()) game.setState(new GameState(new RoomBuilder(0, 1).createRoom(new Player(0, 0))));
        }
    }
}
