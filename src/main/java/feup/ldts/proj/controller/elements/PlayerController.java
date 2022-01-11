package feup.ldts.proj.controller.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Bullet;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.room.Room;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class PlayerController extends GameController {
    private long lastMovement;

    public PlayerController(Room room) {
        super(room);
        this.lastMovement = 0;
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
        else
        if (getModel().isMonster(position))
            getModel().getPlayer().decreaseHP(getModel().getMonsters().get(0).getDamage());
        if (getModel().passageCollision())
            System.out.println("I hit the gate");
    }

    public void shoot() {
        if (getModel().getBullets().size() >= getModel().getPlayer().getWeapon().getCapacity()) return;

        for (Bullet bullet : getModel().getBullets())
            if (bullet.getPosition().equals(getModel().getPlayer().getPosition())) return;

        getModel().getBullets().add(getModel().getPlayer().createBullet());
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.UP) movePlayerUp();
        if (action == GUI.ACTION.DOWN) movePlayerDown();
        if (action == GUI.ACTION.LEFT) movePlayerLeft();
        if (action == GUI.ACTION.RIGHT) movePlayerRight();
        if (action == GUI.ACTION.SHOOT) shoot();
        if (!getModel().getBullets().isEmpty() && time - lastMovement > 250) {
            BulletController controller = new BulletController(getModel());
            controller.step(game, action, time);
            this.lastMovement = time;
        }
    }
}
