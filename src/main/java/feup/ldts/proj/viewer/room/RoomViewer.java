package feup.ldts.proj.viewer.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Element;
import feup.ldts.proj.model.room.Room;
import feup.ldts.proj.viewer.elements.*;
import feup.ldts.proj.viewer.elements.bullets.MonsterBulletViewer;
import feup.ldts.proj.viewer.elements.bullets.PlayerBulletViewer;

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
        drawElements(room.getPlayerBullets(), new PlayerBulletViewer());
        drawElements(room.getMonsterBullets(), new MonsterBulletViewer());
        drawElement(room.getPlayer(), new PlayerViewer());
        if (room.getMonsters().isEmpty())
            drawElement(room.getPassage(), new PassageViewer());

        String HpString = "HP:" + room.getPlayer().getHP() + "/" + room.getPlayer().getMaxHP();
        String CapacityString = " C:" + room.getPlayerBullets().size() + "/" + room.getPlayer().getWeapon().getCapacity();
        gui.drawText(new Position(0, 20), HpString , Game.Colors.get("LightGreen"));
        gui.drawText(new Position(HpString.length()+1, 20), CapacityString, Game.Colors.get("Golden"));


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
            for (int j = 0; j < 20; j++) gui.drawCharacter(j, i,' ', Game.Colors.get("White"));
    }
}
