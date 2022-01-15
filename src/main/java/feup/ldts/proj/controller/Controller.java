package feup.ldts.proj.controller;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException;
}
