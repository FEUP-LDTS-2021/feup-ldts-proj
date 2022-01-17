package feup.ldts.proj.viewer.game.room;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.room.Room;
import feup.ldts.proj.viewer.game.elements.*;
import feup.ldts.proj.viewer.game.elements.bullets.MonsterBulletViewer;
import feup.ldts.proj.viewer.game.elements.bullets.PlayerBulletViewer;

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
        drawElements(room.getItems(), new ItemViewer());
        drawElements(room.getMonsters(), new MonsterViewer());
        drawElements(room.getPlayerBullets(), new PlayerBulletViewer());
        drawElements(room.getMonsterBullets(), new MonsterBulletViewer());
        drawElement(room.getPlayer(), new PlayerViewer());
        if (room.getMonsters().isEmpty())
            drawElement(room.getPassage(), new PassageViewer());

        drawBottomText(room);

        gui.refresh();
    }

    private void drawBottomText(Room room) {
        String HpString = "HP:" + room.getPlayer().getHP() + "/" + room.getPlayer().getMaxHP();
        String timeString = "" + room.getPlayer().getTimeLeft();
        String CapacityString = " C:" + room.getPlayerBullets().size() + "/" + room.getPlayer().getWeapon().getCapacity();
        String timeColor = room.getPlayer().getTimeLeft() < 60 ? Game.Colors.get("Red") : Game.Colors.get("White");


        gui.drawText(new Position(0, 20), HpString , Game.Colors.get("LightGreen"));
        gui.drawText(new Position(HpString.length() + 1, 20), timeString, timeColor);
        gui.drawText(new Position(HpString.length() + timeString.length() + 2, 20), CapacityString, Game.Colors.get("Golden"));
    }

    private <T extends Element> void drawElements(List<T> elements, ElementViewer<T> viewer) {
        for (T element : elements)
            drawElement(element, viewer);
    }

    private <T extends Element> void drawElement(T element, ElementViewer<T> viewer) {
        viewer.draw(element, gui);
    }

    public void drawBackground() {
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) gui.drawCharacter(j, i,' ', Game.Colors.get("White"));
    }
}
