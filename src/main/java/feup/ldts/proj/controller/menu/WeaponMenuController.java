package feup.ldts.proj.controller.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.model.menu.WeaponMenu;
import feup.ldts.proj.states.GameState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLOutput;

public class WeaponMenuController extends Controller<Menu> {

    Player player;
    Weapon newWeapon;

    public WeaponMenuController(Menu menu) {
        super(menu);
    }

    public WeaponMenuController(Menu menu, Player player, Weapon newWeapon) {
        super(menu);
        this.player = player;
        System.out.println("Got into the WeaponMenuController. The player here has " + player.getMaxHP() + " maxHP.");
        this.newWeapon = newWeapon;
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
                if (getModel().isSelected(0)) {
                    player.setWeapon(newWeapon);
                    Game.stateStack.pop();
                }
                else if (getModel().isSelected(1)) {
                    Game.stateStack.pop();
                }
                break;
            case RETURN:
                Game.stateStack.pop();
                break;
            case EXIT:
                game.setState(null);
        }
    }
}
