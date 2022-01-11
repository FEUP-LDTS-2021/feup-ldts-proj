package feup.ldts.proj.states;

import feup.ldts.proj.Game;
import feup.ldts.proj.controller.Controller;
import feup.ldts.proj.controller.room.RoomController;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.viewer.GameViewer;
import feup.ldts.proj.viewer.Viewer;

public class GameState extends State<Room> {
    public GameState(Room room) {
        super(room);
    }

    @Override
    public Viewer<Room> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    public Controller<Room> getController() {
        return new RoomController(getModel());
    }
}
