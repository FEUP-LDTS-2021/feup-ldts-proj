package feup.ldts.proj.controller.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.room.Room;

import java.util.List;

public class MonsterBulletController extends BulletController {
    public MonsterBulletController(Room room) {
        super(room);
    }

    @Override
    protected void moveBullets() {
        List<MonsterBullet> bullets = getModel().getMonsterBullets();
        Player player = getModel().getPlayer();

        for (int i = 0; i < bullets.size(); i++) {
            Position bulletPos = bullets.get(i).moveBullet();
            if (bullets.get(i).isAtLimit() || getModel().isWall(bulletPos)) {
                bullets.get(i).alertObserversDecayed();
                i--;
                continue;
            }

            if (bulletPos.equals(player.getPosition())) {
                player.decreaseHP(bullets.get(i).getDamage());
                bullets.get(i).alertObserversDecayed();
                i--;
                continue;
            }

            bullets.get(i).setPosition(bulletPos);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (!getModel().getMonsterBullets().isEmpty() && time - lastMovement > 250) {
            this.moveBullets();
            this.lastMovement = time;
        }
    }
}

