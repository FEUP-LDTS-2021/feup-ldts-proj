package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Bullet;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class PlayerController extends GameController {
    private long lastMovement;

    public PlayerController(Room room) {
        super(room);
        this.lastMovement = 0;
    }

    public void movePlayerUp() {

    }

    public void movePlayerDown() {

    }

    public void movePlayerLeft() {

    }

    public void movePlayerRight() {

    }

    private void movePlayer(Position position) {

    }

    public void shoot() {

    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {

    }
}
