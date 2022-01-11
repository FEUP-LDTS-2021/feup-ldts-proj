package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastMovement;

    public MonsterController(Room room) {
        super(room);
        this.lastMovement = 0;
    }

    private void moveMonsters() {

    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}
