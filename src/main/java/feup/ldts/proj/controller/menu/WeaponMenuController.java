package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.states.GameState;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeaponMenuController extends Controller<Menu> {

    public WeaponMenuController(Menu menu) {
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
                if (getModel().isSelected(0)) game.setState(new GameState(new RoomBuilder(0, 1).createRoom(new Player(new Position(0, 0)))));
                if (getModel().isSelected(1)) game.setState(null);

            case RETURN:
                Game.stateStack.pop();

            case EXIT:
                game.setState(null);
        }
    }
}
