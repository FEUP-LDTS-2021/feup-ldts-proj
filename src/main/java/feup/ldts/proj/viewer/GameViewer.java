package feup.ldts.proj.viewer;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.viewer.game.room.RoomViewer;

import java.io.IOException;

public class GameViewer extends Viewer<Room> {
    private Room room;

    public GameViewer(Room room) {
        super(room);
        this.room = room;
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        RoomViewer viewer = new RoomViewer(gui);
        viewer.draw(room);
    }
}
