package feup.ldts.proj.controller.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.elements.MonsterController;
import feup.ldts.proj.controller.elements.PlayerController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.model.room.RoomBuilder;
import feup.ldts.proj.states.GameState;
import feup.ldts.proj.states.State;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

import static java.lang.System.exit;

public class RoomController extends GameController {
    private final PlayerController playerController;
    private final MonsterController monsterController;

    public RoomController(Room room) {
        super(room);
        this.playerController = new PlayerController(room);
        this.monsterController = new MonsterController(room);
    }


    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, URISyntaxException {
        if (action == GUI.ACTION.EXIT || getModel().getPlayer().getHP() == 0)
            exit(0);
        else {
            playerController.step(game, action, time);

            if (getModel().passageCollision()) {
                int newDepth = getModel().getDepth() + 1;
                int newRoom = new Random().nextInt(3) + 1;
                game.setState(new GameState(new RoomBuilder(newDepth, newRoom).createRoom(getModel().getPlayer())));
            }

            monsterController.step(game, action, time);
        }
    }
}

