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
        for (Monster monster : getModel().getMonsters()) {
            Position monsterPos = monster.getPosition().getRandomPosition();
            if (!getModel().isWall(monsterPos) && !getModel().isMonster(monsterPos))
                monster.setPosition(monsterPos);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (time - lastMovement > 500) {
            moveMonsters();
            this.lastMovement = time;
        }
    }
}
