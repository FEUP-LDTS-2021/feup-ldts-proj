package feup.ldts.proj.states.menu;

import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.menu.MainMenuController;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.states.State;
import feup.ldts.proj.viewer.Viewer;
import feup.ldts.proj.viewer.menu.MenuViewer;

public class MainMenuState extends State<Menu> {
    public MainMenuState(Menu model) {super(model);}

    @Override
    public Viewer<Menu> getViewer() {return new MenuViewer(getModel());}

    @Override
    public Controller<Menu> getController() {return new MainMenuController(getModel());}
}
