package feup.ldts.proj.controller.game.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.game.elements.MonsterController;
import feup.ldts.proj.controller.game.elements.PlayerController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.states.GameState;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;

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
        if (action == GUI.ACTION.EXIT) {
            game.setState(null);
        }
        if (getModel().getPlayer().getHP() == 0 || getModel().getPlayer().getTimeLeft() == 0) {
            getModel().getPlayer().healHP(getModel().getPlayer().getMaxHP());
            getModel().getPlayer().resetTime();
            game.setState(new GameState(new RoomBuilder(0, 1).createRoom(getModel().getPlayer())));
        }
        else {
            playerController.step(game, action, time);

            if (getModel().isPassage()) {
                int newDepth = getModel().getDepth() + 1;
                int newRoom = new Random().nextInt(3) + 1;
                game.setState(new GameState(new RoomBuilder(newDepth, newRoom).createRoom(getModel().getPlayer())));
            }

            monsterController.step(game, action, time);
            for (Monster monster : getModel().getMonstersToRemove())
                getModel().getMonsters().remove(monster);
        }
    }
}

