package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.elements.observers.BulletObserver;
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
        List<Bullet> bullets = getModel().getBullets();
        List<Monster> monsters = getModel().getMonsters();

        BULLET_LOOP:
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).isAtLimit()) {
                for (BulletObserver observer : bullets.get(i).getObservers())
                    observer.decayed(bullets.get(i));
                i--;
                continue;
            }
            Position bulletPos = bullets.get(i).moveBullet();
            if (getModel().isWall(bulletPos)) {
                for (BulletObserver observer : bullets.get(i).getObservers())
                    observer.decayed(bullets.get(i));
                i--;
                continue;
            }
            for (int j = 0; j < monsters.size(); j++) {
                if (monsters.get(j).getPosition().equals(bulletPos)) {
                    monsters.get(j).decreaseHP(bullets.get(i).getDamage());
                    for (BulletObserver observer : bullets.get(i).getObservers())
                        observer.decayed(bullets.get(i));
                    i--;
                    //goes on to the next bullet
                    continue BULLET_LOOP;
                }
            }
            bullets.get(i).setPosition(bulletPos);
            bullets.get(i).incrementDistanceTravelled();
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (!getModel().getBullets().isEmpty() && time - lastMovement > 250) {
            moveBullets();
            this.lastMovement = time;
        }
    }
}
