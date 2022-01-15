package feup.ldts.proj.controller;

import feup.ldts.proj.model.game.room.Room;

public abstract class GameController extends Controller<Room> {
    public GameController(Room room) {
        super(room);
    }
}
