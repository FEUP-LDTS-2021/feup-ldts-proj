package feup.ldts.proj.controller.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;

public abstract class BulletController extends GameController {
    protected long lastMovement;

    public BulletController(Room room) {
        super(room);
        this.lastMovement = 0;
    }

    protected abstract void moveBullets();
}
