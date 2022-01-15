package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.elements.bullets.BulletController;
import feup.ldts.proj.controller.elements.bullets.PlayerBulletController;
import feup.ldts.proj.controller.elements.observers.BulletObserver;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.bullets.Bullet;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.elements.monsters.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;
import java.net.URISyntaxException;


public class PlayerController extends GameController {
    private long lastMovement;
    private long lastRespawn;

    public PlayerController(Room room) {
        super(room);
        this.lastMovement = 0;
        this.lastRespawn = 0;
    }

    public void movePlayerUp() {
        movePlayer(getModel().getPlayer().getPosition().getUp());
        getModel().getPlayer().setFacingDirection(Element.Direction.UP);
    }

    public void movePlayerDown() {
        movePlayer(getModel().getPlayer().getPosition().getDown());
        getModel().getPlayer().setFacingDirection(Element.Direction.DOWN);
    }

    public void movePlayerLeft() {
        movePlayer(getModel().getPlayer().getPosition().getLeft());
        getModel().getPlayer().setFacingDirection(Element.Direction.LEFT);
    }

    public void movePlayerRight() {
        movePlayer(getModel().getPlayer().getPosition().getRight());
        getModel().getPlayer().setFacingDirection(Element.Direction.RIGHT);
    }

    private void movePlayer(Position position) {

        if (getModel().canExecuteMovement(position))
            getModel().getPlayer().setPosition(position);
        else {
            for (Monster monster : getModel().getMonsters())
                if (monster.getPosition().equals(getModel().getPlayer().getPosition()))
                    monster.bite(getModel().getPlayer());
            for (int i = 0; i < getModel().getMonsterBullets().size(); i++) {
                MonsterBullet bullet = getModel().getMonsterBullets().get(i);
                if (bullet.getPosition().equals(getModel().getPlayer())) {
                    getModel().getPlayer().decreaseHP(bullet.getDamage());
                    bullet.alertObserversDecayed();
                    i--;
                }
            }
        }
    }

    public void shoot() {
        if (getModel().getPlayerBullets().size() >= getModel().getPlayer().getWeapon().getCapacity()) return;

        for (Bullet bullet : getModel().getPlayerBullets())
            if (bullet.getPosition().equals(getModel().getPlayer().getPosition())) return;

        getModel().addPlayerBullet(getModel().getPlayer().createBullet());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.UP) movePlayerUp();
        if (action == GUI.ACTION.DOWN) movePlayerDown();
        if (action == GUI.ACTION.LEFT) movePlayerLeft();
        if (action == GUI.ACTION.RIGHT) movePlayerRight();
        if (action == GUI.ACTION.SHOOT) shoot();
        if (!getModel().getPlayerBullets().isEmpty() && time - lastMovement > 250) {
            BulletController controller = new PlayerBulletController(getModel());
            controller.step(game, action, time);
            this.lastMovement = time;
        }
        if (time - lastRespawn >= 1000) {
            getModel().getPlayer().decreaseTime();
            this.lastRespawn = time;
        }
    }
}
