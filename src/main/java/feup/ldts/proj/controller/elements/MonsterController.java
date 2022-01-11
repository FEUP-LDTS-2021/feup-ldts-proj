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
            if (getModel().canExecuteMovement(monsterPos))
                monster.setPosition(monsterPos);
            else if (getModel().isPlayer(monsterPos))
                getModel().getPlayer().decreaseHP(monster.getDamage());
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
