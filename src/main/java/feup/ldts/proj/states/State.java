package feup.ldts.proj.states;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.viewer.Viewer;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class State<T> {
    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model) {
        this.model = model;
        this.controller = getController();
        this.viewer = getViewer();
    }

    public State(T model, Controller<T> controller) {
        this.model = model;
        this.controller = controller;
        this.viewer = getViewer();
    }

    public abstract Viewer<T> getViewer();

    public abstract Controller<T> getController();

    public T getModel() {
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException, URISyntaxException {
        GUI.ACTION action = gui.getAction();
        controller.step(game, action, time);
        viewer.draw(gui);
    }
}
