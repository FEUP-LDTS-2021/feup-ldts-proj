package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.Menu;

import java.io.IOException;
import java.net.URISyntaxException;

public class WeaponMenuController extends Controller<Menu> {
    Player player;
    Weapon newWeapon;

    public WeaponMenuController(Menu menu, Player player, Weapon newWeapon) {
        super(menu);
        this.player = player;
        this.newWeapon = newWeapon;
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        switch (action) {
            case LEFT: getModel().previousOption(); break;
            case RIGHT: getModel().nextOption(); break;
            case SELECT:
                if (getModel().isSelected(0)) {
                    player.setWeapon(newWeapon);
                    Game.stateStack.pop();
                }
                else if (getModel().isSelected(1)) Game.stateStack.pop();
                break;
            case RETURN: Game.stateStack.pop(); break;
            case EXIT: game.setState(null);
        }
    }
}
