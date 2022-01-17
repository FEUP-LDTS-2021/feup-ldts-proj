package feup.ldts.proj.states;

import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.menu.ControlsMenuController;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.viewer.Viewer;
import feup.ldts.proj.viewer.menu.MenuViewer;


public class ControlsMenuState extends State<Menu> {

    public ControlsMenuState(Menu model) {super(model);}

    @Override
    public Viewer<Menu> getViewer() {return new MenuViewer(getModel());}

    @Override
    public Controller<Menu> getController() {return new ControlsMenuController(getModel());}
}
