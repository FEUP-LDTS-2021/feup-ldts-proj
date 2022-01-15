package feup.ldts.proj.controller.game.elements.bullets;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.bullets.PlayerBullet;
import feup.ldts.proj.model.game.room.Room;

import java.util.List;

public class PlayerBulletController extends BulletController {
    public PlayerBulletController(Room room) {
        super(room);
    }

    @Override
    protected void moveBullets() {
        List<PlayerBullet> bullets = getModel().getPlayerBullets();
        List<Monster> monsters = getModel().getMonsters();

        BULLET_LOOP:
        for (int i = 0; i < bullets.size(); i++) {
            Position bulletPos = bullets.get(i).moveBullet();
            if (bullets.get(i).isAtLimit() || getModel().isWall(bulletPos)) {
                bullets.get(i).alertObserversDecayed();
                i--;
                continue;
            }

            for (int j = 0; j < monsters.size(); j++) {
                if (monsters.get(j).getPosition().equals(bulletPos)) {
                    monsters.get(j).decreaseHP(bullets.get(i).getDamage());
                    bullets.get(i).alertObserversDecayed();
                    i--;
                    //goes on to the next bullet
                    continue BULLET_LOOP;
                }
            }
            bullets.get(i).setPosition(bulletPos);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        this.moveBullets();
    }
}
