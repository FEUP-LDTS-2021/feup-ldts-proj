package feup.ldts.proj.states;

import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.menu.WeaponMenuController;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.viewer.Viewer;
import feup.ldts.proj.viewer.menu.MenuViewer;

public class WeaponMenuState extends State<Menu>{

    public WeaponMenuState(Menu model) {
        super(model);
    }

    @Override
    public Viewer<Menu> getViewer() {return new MenuViewer(getModel());}

    @Override
    public Controller<Menu> getController() {
        return new WeaponMenuController(getModel());
    }
}
