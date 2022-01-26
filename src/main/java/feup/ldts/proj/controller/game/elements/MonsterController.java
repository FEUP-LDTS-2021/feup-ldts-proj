package feup.ldts.proj.controller.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.game.elements.bullets.MonsterBulletController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

import java.io.IOException;

public class MonsterController extends GameController {
    private long lastAction;
    private long lastBulletMovement;

    public MonsterController(Room room) {
        super(room);
        this.lastAction = 0;
        this.lastBulletMovement = 0;
    }

    private void moveMonsters() {
        for (int i = 0; i < getModel().getMonsters().size(); i++) {
            Monster monster = getModel().getMonsters().get(i);
            int iniSize = getModel().getMonsters().size();

            monster.move(getModel());

            if (iniSize != getModel().getMonsters().size()) //checks if the monster has been removed (because he stepped on a playerBullet)
                i--;
        }
    }

    private void makeMonstersAttack() {
        for (Monster monster : getModel().getMonsters())
            monster.attack(getModel().getPlayer(), getModel());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        MonsterBulletController controller = new MonsterBulletController(getModel());

        if (time - lastAction > 500) {
            makeMonstersAttack();
            moveMonsters();
            this.lastAction = time;
        }

        if (time - lastBulletMovement > 250 && !getModel().getMonsterBullets().isEmpty()) {
            controller.step(game, action, time);
            lastBulletMovement = time;
        }
    }
}
