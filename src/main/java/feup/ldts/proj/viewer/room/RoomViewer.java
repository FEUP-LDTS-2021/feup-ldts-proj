package feup.ldts.proj.viewer.room;

import com.googlecode.lanterna.TextColor;
import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.elements.Monster;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.viewer.elements.*;

import java.io.IOException;
import java.util.List;

public class RoomViewer {
    private final GUI gui;

    public RoomViewer(GUI gui) {
        this.gui = gui;
    }

    public void draw(Room room) throws IOException {
        gui.clear();

        drawBackground();
        drawElements(room.getWalls(), new WallViewer());
        drawElements(room.getMonsters(), new MonsterViewer());
        //drawElements(room.getBullets(), new BulletViewer());
        drawElement(room.getPlayer(), new PlayerViewer());
        gui.drawText(new Position(0, 20), "HP: " + room.getPlayer().getHP(), Game.Colors.get("White"));

        gui.refresh();
    }

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    GUI.ACTION getAction() throws IOException {
        return gui.getAction();
    }

    public void drawBackground() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) gui.drawCharacter(j, i,' ', "#ffffff");
    }
}
