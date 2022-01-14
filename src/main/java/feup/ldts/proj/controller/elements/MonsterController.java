package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.elements.bullets.MonsterBulletController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastMovement;
    private long lastBulletMovement;

    public MonsterController(Room room) {
        super(room);
        this.lastMovement = 0;
        this.lastBulletMovement = 0;
    }

    private void moveMonsters() {
        for (Monster monster : getModel().getMonsters()) {
            Position monsterPos = monster.getPosition().getRandomPosition();
            if (!getModel().isWall(monsterPos) && !getModel().isMonster(monsterPos))
                monster.setPosition(monsterPos);
        }
    }

    private void makeMonstersAttack() {
        for (Monster monster : getModel().getMonsters())
            monster.attack(getModel().getPlayer(), getModel());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        MonsterBulletController controller = new MonsterBulletController(getModel());

        if (time - lastMovement > 500) {
            moveMonsters();
            makeMonstersAttack();
            this.lastMovement = time;
        }

        if (time - lastBulletMovement > 250 && !getModel().getMonsterBullets().isEmpty()) {
            controller.step(game, action, time);
            lastBulletMovement = time;
        }
    }
}
