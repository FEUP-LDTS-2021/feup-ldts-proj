package feup.ldts.proj.states;

import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.menu.WeaponMenuController;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.model.menu.WeaponMenu;
import feup.ldts.proj.viewer.Viewer;
import feup.ldts.proj.viewer.menu.MenuViewer;

public class WeaponMenuState extends State<Menu>{
    Player player;
    Weapon newWeapon;

    public WeaponMenuState(Menu model, Player player, Weapon newWeapon) {
        super(model, new WeaponMenuController(model, player, newWeapon));
        this.player = player;
        this.newWeapon = newWeapon;
    }

    @Override
    public Viewer<Menu> getViewer() {return new MenuViewer(getModel());}

    @Override
    public Controller<Menu> getController() {
        return new WeaponMenuController(getModel(), player, newWeapon);
    }
}
