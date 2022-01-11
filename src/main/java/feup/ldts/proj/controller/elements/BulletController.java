package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Bullet;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;
import java.util.List;

public class BulletController extends GameController {
    private long lastMovement;

    public BulletController(Room room) {
        super(room);
        this.lastMovement = 0;
    }

    private void moveBullets() {

    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}
