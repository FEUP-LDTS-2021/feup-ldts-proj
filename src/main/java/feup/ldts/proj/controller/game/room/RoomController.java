package feup.ldts.proj.controller.game.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.GameController;
import feup.ldts.proj.controller.game.elements.MonsterController;
import feup.ldts.proj.controller.game.elements.PlayerController;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.model.game.room.RoomBuilder;
import feup.ldts.proj.model.menu.VictoryMenu;
import feup.ldts.proj.states.GameState;
import feup.ldts.proj.states.VictoryMenuState;

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
        if (action == GUI.ACTION.RETURN) {
            Game.stateStack.pop();
        }
        if (getModel().getPlayer().getHP() == 0 || getModel().getPlayer().getTimeLeft() == 0) {
            getModel().getPlayer().healHP(getModel().getPlayer().getMaxHP());
            getModel().getPlayer().resetTime();
            Game.stateStack.pop();
            Game.stateStack.push(new GameState(new RoomBuilder(0, 1).createRoom(getModel().getPlayer())));
        } else {
            playerController.step(game, action, time);

            if (getModel().isPassage()) {
                int newDepth, newRoom;
                if (getModel().getDepth() == 2) {
                    newDepth = 3;
                    newRoom = 1;
                } else if (getModel().getDepth() == 3) {
                    Game.stateStack.push(new VictoryMenuState(new VictoryMenu()));
                    return;
                } else {
                    newDepth = getModel().getDepth() + 1;
                    newRoom = new Random().nextInt(3) + 1;
                }
                Game.stateStack.pop();
                Game.stateStack.push(new GameState(new RoomBuilder(newDepth, newRoom).createRoom(getModel().getPlayer())));
            }

            monsterController.step(game, action, time);
        }
    }
}

